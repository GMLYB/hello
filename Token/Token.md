# Token

### 1 Cookie的缺点

* cookie会被窃取，不安全
* 在某些浏览器中，cookie可以直接禁止使用



### 2 Token

![token](.\imgs\token.png)



### 3 后端登录操作

#### （1）登录创建token

```
@PostMapping("login")
public Result login(@RequestParam String userName, @RequestParam String password){
	User user = userService.getUserLogin(UserName,password);
	if(user != null){
		//登录成功
		//生成令牌
		String token = UUID.randomUUID + "";
		//存到Redis数据库中 30分钟时效 这个Redis是直接从容器中获取得到的
		redisTemplate.opsForValue().set(token,user,Duration.ofMinutes(30L));
		
		//将token传送到前端
		//value message code 
		return new Result(token,"登录成功",100);
	}
	
	return new Result(null,"登录失败",104);
}
```



#### （2）token获取登录用户 

```java
@GetMapping("getUserOfLogin")
public Result getUserOfLogin(HttpServletRequest request) throws Unsuppor..{
    //token通过请求头传送
    String token = request.getHeader("token");
    
    //通过redis中的token获得user信息 
    redisTemplate.opsForValue.get(token);
    
    if(user != null){
        return new Result(user,"获取成功",100);
    }
    
    return new Result(null,"获得用户失败",104);
}
```



#### （3）添加个过滤器

```java
@Override
public void doFilter(ServletRequest servletRequest, ServletResponse ServletResponse){
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) ServletResponse;
    
    //Filter过滤器跨域处理
    String origin = request.getHeader("Origin");
    response.setHeader("Access-Control-Allow-Origin",origin);
    ...
        
    //从Redis中获得token
    String token = request.getHeader("token"); 
    //判断一下前台是否传送了token，没传送就将token置为空
    if(token == null){
        token = "";
    }
    
    //查看是否存在
    Long expire = redisTemplate.getExpire(token);
    //剩余时间大于0，就表示存在
    if(expire > 0){
        //重置token的时间 参数 时间 单位
        redisTemplate.expire(token,30L,TimeUnit.MINUTES);
        //放行
        filterChain.doFilter(ServletRequest,ServletResponse);
    }else{
        //未登录
        String string = JSONObject.toJSONString(new Result(null,"未登录",103));
        response.setContentType("json/text;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(string);
    } 
}

```

