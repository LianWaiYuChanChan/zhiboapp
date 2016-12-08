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
        "name":"aaa",
        "accountId":"1233444",
        "public":false
    }
   ```
   * Response:
    + Response status: 200(OK) 
    + Response Body:
   ```json
    {
       "name": "aaa",
       "pushUrl": "rtmp://aaa.uplive.ks-cdn.com/live/stream?signature=TZPCPTi3ZZci1f17AfEooTfs2iU=&accessKey=aceesKeyValueFromWhere&expire=1481226087&nonce=TODO&vdoid=123",
       "pullUrl": "rtmp://aaa.rtmplive.ks-cdn.com/live/stream",
       "status": "INITIALIZED",
       "id": 3,
       "public": false
    }
   ```