<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div id="LoginAutoModal">
		<div
        class="modal fade"
        id="LoginModal"
        data-bs-backdrop="static"
        data-bs-keyboard="false"
        aria-hidden="true"
      >
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <div class="text-primary">
                <i class="bi bi-shield-lock-fill"></i>
                <span class="ms-1 fs-5 fw-bold">로그인 창</span>
              </div>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>

            <div class="modal-body">
              <form>
                <div class="mb-3 row">
                  <label for="staticEmail" class="col-sm-2 col-form-label text-body">Email</label>
                  <div class="col-sm-10">
                    <input type="text" name="email" class="form-control" id="check-Email" />
                  </div>
                </div>

                <div class="mb-3 row">
                  <label for="inputPassword" class="text-body col-sm-2 col-form-label"
                    >Password</label
                  >
                  <div class="col-sm-10">
                    <input type="password" name="password" class="form-control" id="check-Password" />
                  </div>
                </div>
			<div class="modal-footer justify-content-between">
              <a target="_blank" href="${root}/user?action=mvjoin" type="button" class="text-body fs-6"
                >아이디가 없으신가요?</a
              >
              <button id="loginRequest" type="button" class="btn btn-primary">로그인 하기</button>
            </div>
            </form>
              
                
                
               <!--  <input id="loginRequest" class="btn btn-primary float-right" value = "로그인 하기">
              </form>
            </div>
            <div class="modal-footer justify-content-between">
              <a target="_blank" href="join.jsp" type="button" class="text-body fs-6"
                >아이디가 없으신가요?</a
              > -->
              
              
              
              
              
             
            </div>
          </div>
        </div>
      </div>
	</div>