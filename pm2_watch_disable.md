# pm2 watch가 동작하지 않을 때


1. test setting

package.json
```javascript
{
    "name": "test1",
    "version": "1.0.0",
    "description": "",
    "main": "app.js",
    "scripts": {
    },
    "author": "",
    "license": "ISC",
    "dependencies": {
        "express": "^4.17.1"
    }
}
```

app.js
```javascript
const express = require('express');

const app = express();
const port = 3000;

app.listen(port, async () => {
    console.log('test1 start');
});
```

2. ecosystem 파일을 생성

```
pm2 ecosystem
```

ecosystem 파일에는 watch 설정만 했습니다.
ecosystem.config.js
```javascript
module.exports = {
    apps: [{
        name: 'test1',
        script: 'app.js',
        watch: true
    }],
};
```

3. 테스트

위 상태에서 ecosystem 파일을 이용해 서버를 구동합니다.
```
pm2 start ecosystem.config.js
```

로그를 보면 정상적으로 구동된 것을 확인할 수 있습니다.
```
pm2 log
```

이 상태에서 코드를 수정하면 자동적으로 pm2가 재시작하면서 수정된 코드가 반영됩니다.
app.js
```javascript
app.listen(port, async () => {
    console.log('test1 start');
    console.log('modify code');
});
```

!!!
```
PM2      | Change detected on path app.js for app test1 - restarting
PM2      | Stopping app:test1 id:0
PM2      | App [test1:0] exited with code [1] via signal [SIGINT]
PM2      | pid=412 msg=process killed
PM2      | App [test1:0] starting in -fork mode-
PM2      | App [test1:0] online
0|test1  | test1 start
0|test1  | modify code
```

그런데 pm2 stop을 하고 다시 start하면 watch가 동작하지 않아서 코드를 수정해도 restart가 일어나지 않습니다.

```
pm2 stop ecosystem.config.js // 정지 후
pm2 start ecosystem.config.js // 다시 시작
```

4. 해결

좀 더 세련된 방법이 있을지는 모르겠으나 어쨌든 제가 찾아서 해결한 방법은 다음과 같습니다.
```
pm2 stop후 pm2 delete를 통해 프로세스를 아예 삭제 후 start하면 watch가 정상적으로 동작합니다.
```

```
pm2 stop ecosystem.config.js && pm2 delete ecosystem.config.js // 정지 및 삭제
pm2 start ecosystem.config.js // 다시 시작
```









