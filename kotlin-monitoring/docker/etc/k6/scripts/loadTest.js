import http from 'k6/http';
import { check, group, sleep } from 'k6';

export const options = {
    stages: [
        { duration: '1m', target: 100 }, // simulate ramp-up of traffic from 1 to 100 users over 5 minutes.
        { duration: '2m', target: 100 }, // stay at 100 users for 10 minutes
        { duration: '1m', target: 0 }, // ramp-down to 0 users
    ],
    thresholds: {
        'http_req_duration': ['p(99)<1500'], // 99% of requests must complete below 1.5s
        // 'logged in successfully': ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'http://host.docker.internal:8080';


export default () => {
    const loginRes = http.post(`${BASE_URL}/log`);

    check(loginRes, {
        'logged in successfully': (resp) => resp.json('access') !== '',
    });
    sleep(1);
};
