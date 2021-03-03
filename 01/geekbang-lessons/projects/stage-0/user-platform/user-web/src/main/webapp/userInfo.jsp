<head>
    <jsp:directive.include file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
    <jsp:directive.include file="/WEB-INF/jsp/prelude/include-css.jspf" />
    <jsp:directive.include file="/WEB-INF/jsp/prelude/include-js.jspf" />
</head>
<body style="height:100%">

<div class="container h-100">
  <div class="row align-items-center h-100">
  <div class="col-lg-4 col-md-4 mb-4 mx-auto">
    <img src=${requestScope.head} />
    <p>—— 幸会啊。论成败，人生豪迈，大不了，重头再来。</p>
    <hr>
    <h3>${requestScope.userName}</h3>
    <hr>
    <h3>年龄：${requestScope.age}</h3>
    <hr>
    <h3>称号：${requestScope.title}</h3>
    <hr>
    <h3>个性签名：${requestScope.autograph}</h3>
  </div>
  </div>
</div>

</body>