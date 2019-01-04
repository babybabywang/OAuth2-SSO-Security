# OAuth2-SSO-Security
授权+认证+第三方授权登录DEMO
1. SpringCloud Security+SpringCloud OAuth2+JWT生成token
2. password模式和client端模式
3. password生成token:http://localhost:8080/oauth/token?client_id=client_2&username=user_1&password=123456&grant_type=password&scope=select
4. client_credentials生成token:http://localhost:8080/oauth/token?client_id=client_1&secret=123456&grant_type=client_credentials&scope=select
5. JWT为对称密码方式
6. http://127.0.0.1:8080/user?access_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib3JkZXIiXSwic2NvcGUiOlsic2VsZWN0Il0sImV4cCI6MTU0NjYzNjEzMCwiYXV0aG9yaXRpZXMiOlsiY2xpZW50Il0sImp0aSI6ImVhNmQ2YzdkLTlhMDctNDNkOS04NzAwLTg3NDk2OGQxM2FlNCIsImNsaWVudF9pZCI6ImNsaWVudF8xIn0.MDh3tfuZp4k5Yp8gVVClBnJ5CvMXu8jbctYgME6QHDI
得到用户信息