## 哺乳纲生物管理系统

### 一、目录结构

- `backend`: 后端项目，SSM + SpringBoot
- `frontend`: 前端项目，solidjs + unocss
- `assets`: 储存文档中需要使用的资源文件，如图片。

### 二、系统概要

#### 1. 系统的基本结构

[![p9Xw12V.png](https://s1.ax1x.com/2023/05/29/p9Xw12V.png)](https://imgse.com/i/p9Xw12V)

#### 2. 功能需求

[![p9Xw6qe.png](https://s1.ax1x.com/2023/05/29/p9Xw6qe.png)](https://imgse.com/i/p9Xw6qe)

### 三、前后端消息协议

1. 均采用 JSON 作为信息交换格式
2. 基本字段

   ```typescript
   interface Response {
     // code: 状态码，没有特别定义的情况下，与[HTTP Status Code](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status)保持一直，一定要做到状态码有语义。
     code: number;
     // 携带的消息，成功可确定为：Success，失败则包含失败的信息。
     message: string;
     // data: 携带请求结果的信息，可为空
     data?: any;
   }
   ```

3. 对于分页的信息，在基本字段的前提下，data 的类型为 Pagination:

```typescript
interface Pagination<T> {
  // 数据项
  list: Array<T>;
  page: {
    // 当前页号，从1开始
    current: number;
    // 总页数
    total: number;
    // 一页所包含的项目数量
    pageSize: number;
  };
}
```

4. 对于分页的请求，包含以下 Query Parameter:
```typescript
interface QueryParameter {
  // 请求的页码
  current?: number;
  // 页面大小
  pageSize?: number;
}
```
5. 对于API的设计风格，比如有一个资源，在最基本的增删改查中，应该以下面的格式进行
 ```
   GET     /user/:id  根据id查询一个User的详细信息
   GET     /user      查询所有的User，以分页的形式返回
   POST    /user      增加一个User
   PUT     /user/:id  更新一个User的信息，注意这个PUT请求会上传一整个User的信息，包括没有改变的字段。
   DELETE  /user/:id  删除一个User
```