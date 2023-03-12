package com.lab.sharding.config.shard.aspect

import com.lab.sharding.config.shard.TransactionShardingGroupManager
import com.lab.sharding.config.shard.TransactionShardingManager
import com.lab.sharding.config.shard.properties.ShardingRuleProperty
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.context.expression.MethodBasedEvaluationContext
import org.springframework.core.DefaultParameterNameDiscoverer
import org.springframework.core.ParameterNameDiscoverer
import org.springframework.expression.ExpressionParser
import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.stereotype.Component

@Component
@Aspect
class ShardAspect(
    private val shardingRuleProperty: ShardingRuleProperty
) {
    private val expressionParser: ExpressionParser = SpelExpressionParser()
    private val nameDiscoverer: ParameterNameDiscoverer = DefaultParameterNameDiscoverer()

    @Around("@annotation(ShardKey)")
    fun handler(joinPoint: ProceedingJoinPoint): Any {
        val method = (joinPoint.signature as MethodSignature).method
        if (joinPoint.args.isEmpty()) {
            throw RuntimeException("ShardAspect.process(), arguments is empty")
        }

        val evaluationContext = MethodBasedEvaluationContext(joinPoint.args[0], method, joinPoint.args, nameDiscoverer)
        val annotation = method.getAnnotation(ShardKey::class.java)
        val annotationKey = expressionParser.parseExpression(annotation.key)
            .getValue(evaluationContext)
            .toString()

        return try {
            TransactionShardingManager.setCurrentTransactionSharding(true)
            TransactionShardingGroupManager.setCurrentTransactionShardingGroup(
                annotation.target,
                shardingRuleProperty.calculateShardKey(annotationKey)
            )
            joinPoint.proceed()
        } finally {
            TransactionShardingManager.refresh()
            TransactionShardingGroupManager.refresh()
        }
    }
}
