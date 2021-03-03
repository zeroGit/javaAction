<head>
    <jsp:directive.include file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
    <jsp:directive.include file="/WEB-INF/jsp/prelude/include-css.jspf" />
    <jsp:directive.include file="/WEB-INF/jsp/prelude/include-js.jspf" />
    <script>
        function presubmit(){
            var errstr = "";
            var name = document.getElementById("regName").value
            if (name == "") {
                errstr += "邮箱/手机号 不能为空";
                showErr(errstr);
                return false;
            }

            var pwd = document.getElementById("regPasswd").value
            if (pwd == "") {
                errstr += "密码 不能为空";
                showErr(errstr);
                return false;
            }

            var pwd2 = document.getElementById("regPasswdConfirm").value
            if (pwd2 == "") {
                errstr += "密码确认 不能为空";
                showErr(errstr);
                return false;
            }

            if (pwd != pwd2) {
                errstr += "密码 和 密码确认 不一致";
                showErr(errstr);
                return false;
            }

            return true;
        }
        function showErr(errstr) {
            alert(errstr);
        }
    </script>
<title>My Home Page - register</title>
</head>
<body style="height:100%">
    <div id="modalHint" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
      <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content" sytle="width:100%">
            <p></p>
            <p id="modalHintContent" class="text-center"></p>
        </div>
      </div>
    </div>

<div class="container h-100">
  <div class="row align-items-center h-100">
  <div class="col-lg-4 col-md-4 mb-4 mx-auto">
    <div id="reg_block" style="height:100%">
            <form id="regForm" class="form" action="register" method="Post" name="regForm" onsubmit="return presubmit()">
                <div id="regNameWrap" class="form-group has-feedback" >
                    <input type="text" class="form-control input-lg " name="regName" id="regName" placeholder="邮箱/手机号">
                    <span id="regNameSpan" class="glyphicon form-control-feedback " style="padding-right:15px" aria-hidden="true"></span>
                    <div id="regNameAlert" class="hidden alert alert-danger alert-dismissible fade in " role="alert" ></div>
                </div>
                <div id="regPasswdWrap" class="form-group has-feedback">
                    <input type="password" class="form-control input-lg" name="regPasswd" id="regPasswd"  placeholder="密码">
                    <span id="regPasswdSpan" class="glyphicon form-control-feedback " style="padding-right:15px" aria-hidden="true"></span>
                    <div id="regPasswdAlert" class="hidden alert alert-danger alert-dismissible fade in " role="alert" ></div>
                </div>
                <div id="regPasswdConfirmWrap" class="form-group has-feedback">
                    <input type="password" class="form-control input-lg" name="regPasswdConfirm" id="regPasswdConfirm"  placeholder="确认密码">
                    <span id="regPasswdConfirmSpan" class="glyphicon form-control-feedback " style="padding-right:15px" aria-hidden="true"></span>
                    <div id="regPasswdConfirmAlert" class="hidden alert alert-danger alert-dismissible fade in " role="alert" ></div>
                </div>
                <div class="form-group">
                    <button id="regSubmit" type="submit" class="btn-block btn btn-primary btn-lg">注册</button>
                </div>
            </form>
        </div>
        </div>
    </div>
    </div>

</body>
