## livestream
### create livestream
   * Request:
    + Request method, url, headers
   ```javascript
   HTTP Method: POST
   URL: http://127.0.0.1:8080/zhiboapp/api/livesteam
   Headers: Content-Type:application/json
   ```
    + Request body:
   ```json
    {
        "name":"mylivestream",
        "account":{ "id":1},
        "public":false
    }
   ```
   * Response:
    + Response status: 200(OK)
    + Response Body:
   ```json
    {
       "name": "mylivestream",
       "pushUrl": "rtmp://mylivestream.uplive.ks-cdn.com/live/stream?signature=Mv+ZRDthGgBNCcv6kOxJtO6NcsI=&accessKey=aceesKeyValueFromWhere&expire=1481360724&nonce=TODO&vdoid=123",
       "pullUrl": "rtmp://mylivestream.rtmplive.ks-cdn.com/live/stream",
       "status": "INITIALIZED",
       "host":
       {
           "id": 1
       },
       "id": 2,
       "public": false
    }
   ```
### watch livestream
   * Request:
    + Request method, url, headers
   ```javascript
   HTTP Method: POST
   URL: http://127.0.0.1:8080/zhiboapp/api/account/6/watch
   Headers: Content-Type:application/json
   ```
    + Request body:
   ```json
    {
    "liveStream":{"id":1}
    }
   ```
   * Response:
    + Response status: 204(No-Content)
    + Response Body: NA