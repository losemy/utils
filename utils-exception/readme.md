
## 常用异常处理整理

## CommonException 

返回的异常需要尽可能的让使用aop能够直接处理，并且可以返回给调用方详细的问题所在
>>定义基础异常类,其他类都需要继承他 来描述具体的含义

>> DALException 数据层异常

>> ServiceException service层异常

>> ValidateException 数据校验异常

>> DubboException 调用dubbo服务异常

>> HttpException 调用http服务异常 。。。 枚举更多

>> 其他Exception未知exception 不知道该如何处理  （系统异常（非受检））
