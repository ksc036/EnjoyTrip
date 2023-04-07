fetch("./trip?action=loadLocation", { method: "GET" })
  .then((response) => response.json())
  .then((data) => makeOption(data));

function makeOption(data) {
  let areas = data.items;
  let sel = document.getElementById("search-area");
  areas.forEach((area) => {
    let opt = document.createElement("option");
    opt.setAttribute("value", area.code);
    opt.appendChild(document.createTextNode(area.name));
    sel.appendChild(opt);
  });
}

// 사진칼럼만들 태그
let pictureLst = document.getElementById("pictureLst");
// 랜덤으로 게시글 가져오는 코드


/*fetch("./trip?action=loadRandomInfo", { method: "GET" })
  .then((response) => response.json())
  .then((data) => {
    makeList(data);
  });*/

// 검색버튼 누르기 클릭 이벤트 등록
document.getElementById("btn-search").addEventListener("click", () => {
	
  let searchUrl = "./trip?action=search";
  let sido_code = document.getElementById("search-area").value;
  let content_type_id = document.getElementById("search-content-id").value;
  let keyword = document.getElementById("search-keyword").value;
  
  if (parseInt(sido_code)) {
    searchUrl += `&sido_code=${sido_code}`;
  }
  if (parseInt(content_type_id)) {
    searchUrl += `&content_type_id=${content_type_id}`;
  }
  if(!keyword){
	  alert("키워드를 입력하세요");
	  return;
  }
   searchUrl += `&keyword=${keyword}`;
  fetch(searchUrl, { method: "GET" })
    .then((response) => response.json())
    .then((data) => {
      makeList(data);
    });
});

function makeList(data) {
  let trips = data.items; // 배열
  pictureLst.innerHTML = "";
  let pictureLstContent = ``;
  if (trips.length == 0) {
    let noData = document.createElement("h1");
    noData.textContent = "Content가 없습니다.";
    pictureLst.appendChild(noData);
    document.getElementById("btn-close").click();
    return;
  } else {
    // 이미지 있는거 넣고 없으면 default 넣기
    trips.forEach((trip) => {
      let img = trip.firstimage;
      if (!img) {
        img = trip.firstimage2;
      }
      if (!img) {
        img =
          "https://www.shutterstock.com/image-vector/photo-coming-soon-symbol-600w-161251868.jpg";
      }
      pictureLstContent += `
		        <div class="col-xl-3 col-lg-4 col-md-6" id=${trip.contentid}>
		            <div class="gallery-item h-100">
		              <img style="max-height: 280px; width : 100%" src="${img}" class="img-fluid" alt="" />
		              <div class="gallery-links d-flex align-items-end justify-content-between">
		                <span class="align-self-start mt-5 ms-5">${trip.title}</span>
		                <div class="me-5 mb-5">
		                  <a href="./trip?action=goMap&latitude=${trip.latitude}&longitude=${trip.longitude}&title=${trip.title}" target="_blank" title="Gallery 1" class="glightbox preview-link"><i
		                      class="bi bi-geo-alt-fill"></i>
		                  </a>
		                      <i class="bi bi-link-45deg" onclick="view(${trip.contentid});"></i>
		                  <a href=""><i class="bi bi-hand-thumbs-up preview-link"></i></a>
		                  <a href=""><i class="bi bi-hand-thumbs-up-fill preview-link"></i>
		                  </a>
		                </div>
		              </div>
		            </div>
		          </div>
		        `;
    });
    pictureLst.innerHTML = pictureLstContent;
  }
  // 검색누르면 모달 닫기
  document.getElementById("btn-close").click();
}

//누르면 해당 게시글을 달창으로 보여주게 하기
function view(contentid) {
  const myModal = new bootstrap.Modal("#viewDetail", {
    keyboard: false,
  });
  
  fetch("./trip?action=viewDetail&content_id="+contentid, { method: "GET" })
  .then((response) => response.json())
  .then((data) => {
	  document.getElementById("viewDetailTitle").innerHTML = data.title;
	  let img = data.firstimage;
	  if (!img) {
		  img = data.firstimage2;
	  }
	  if (!img) {
		  img = "https://www.shutterstock.com/image-vector/photo-coming-soon-symbol-600w-161251868.jpg";
	  }
	  
	  document.getElementById(
			  "viewDetailBodyImage"
	  ).innerHTML = `<img src="${img}" class="img-fluid" alt="...">`;
	  
	  let addr = data.addr1;
	  if (!addr) {
		  addr = data.add2;
	  }
	  if (!addr) {
		  addr = "해당 지점은 주소가 등록되지 않았습니다..";
	  }
	  document.getElementById("viewDetailDescription").innerHTML ="<p>" + data.description + "</p>";
	  document.getElementById("viewDetailBodyAddress").innerHTML = "Address : " + addr;
	  let tel = data.tel;
	  if (!tel) {
		  tel = "해당 지점은 번호가 등록되지 않았습니다..";
	  }
	  document.getElementById("viewDetailBodyTel").innerHTML = "Tel : " + tel;
	  myModal.show();
  });

}