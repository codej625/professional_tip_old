# jQuery ajax를 활용하여 간단 CRUD 게시판을 만들어보자!

1. sql
```sql
CREATE TABLE IF NOT EXISTS `management` (
  `seq` int NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `type` varchar(50) COMMENT '계정 과목',
  `management_number` varchar(50) COMMENT '관리 번호',
  `name` varchar(50) COMMENT '자산명',
  `model_name` varchar(50) COMMENT '형식(모델명)',
  `quantity` varchar(20) COMMENT '수량',
  `create_date` varchar(50) COMMENT '취득 일자',
  `price` varchar(20) COMMENT '취득금액',
  `purchase` varchar(20) COMMENT '구입처',
  `department` varchar(20) COMMENT '관리 부서',
  `user` varchar(20) COMMENT '사용자',
  `note` varchar(500) COMMENT '비고',
  `description` varchar(20) COMMENT '설명',
  /* title columns */
  `update_date` varchar(20) COMMENT '수정일자',
  `company_name` varchar(20) COMMENT '회사명',
  PRIMARY KEY (`seq`)
);
```
2. html
```html
<!DOCTYPE html>
<html>
<head>
  <title>s</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
  <body id="page-top">
    <div id="wrapper">
      <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
          <div class="container-fluid">
            <div th:fragment="frag_body" class="col-md-12">
              <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                  <li class="breadcrumb-item">MPLANIT</li>
                  <li class="breadcrumb-item">내부업무서비스</li>
                  <li class="breadcrumb-item active" aria-current="page">자산관리대장(장비)</li>
                </ol>
              </nav>
              <div class="card shadow mb-4">
                <div class="card-body">
                  <div class="d-flex flex-row justify-content-between mb-4">
                    <div>
                      <input 
                        id="searchInput" 
                        class="btn-outline-dark form-control" 
                        oninput="searchName();"
                        value=""
                        placeholder="Search Name">
                    </div>
                    <div>
                      <button 
                        id="excelDownload" 
                        class="btn btn-light download"
                        type="button" 
                        onclick="exportExcel();"
                      >
                        Excel
                      </button>
                      <button 
                        class="btn btn-light" 
                        onclick="addRow();"
                      >
                        Add
                      </button>
                    </div>
                  </div>
                  <div id="addInput">
                    <table>
                    
                    </table>
                  </div>
                  <table id="tableData" class="table table-hover text-center">
                    <colgroup>
                      <col width="*">
                      <col width="*">
                      <col width="*">
                      <col width="*">
                      <col width="*">
                      <col width="*">
                      <col width="*">
                      <col width="*">
                      <col width="*">
                      <col width="*">
                      <col width="*">
                      <col width="9.5%">
                    </colgroup>
                    <thead class="thead-light">
                      <tr>
                        <th>계정과목</th>
                        <th>관리번호</th>
                        <th>자산명</th>
                        <th>형식(모델명)</th>
                        <th>수량</th>
                        <th>취득일자</th>
                        <th>취득금액</th>
                        <th>구입처</th>
                        <th>사용부서</th>
                        <th>사용자</th>
                        <th>비고</th>
                        <th></th>
                      </tr>
                    </thead>
                    <tbody id="create-table-row">
                    
                    </tbody>
                  </table>
                </div>
              </div>
              <!-- Button trigger modal -->
              <button 
                type="button" 
                class="btn btn-primary d-none modal-open" 
                data-toggle="modal" 
                data-target="#tableModal"
              ></button>
              <!-- Modal -->
              <div class="modal fade" id="tableModal" tabindex="-1" aria-labelledby="#tableModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title" id="tableModalLabel">Update</h5>
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                      </button>
                    </div>
                    <div class="modal-body d-flex justify-content-center">
                      
                    </div>
                    <div class="modal-footer">
                      <button 
                        class="btn btn-light close-btn" 
                        type="button" 
                        data-dismiss="modal"
                      >
                        Close
                      </button>
                      <button 
                        class="btn btn-light" 
                        type="button" 
                        onclick="updateRow();"
                      >
                        Save changes
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Sheet JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.14.3/xlsx.full.min.js"></script>
    <!--FileSaver savaAs 이용 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/1.3.8/FileSaver.min.js"></script>
  </body>
</html>
```
3. javascript
```javascript
(() => {
  createTable();
})();
//=====================================================================================
function createTable(name) {
  
  let url;
  (name === undefined) ? url = '/select_management' : url = `/select_management?name=${name}`;
  
  const table = document.querySelector('#create-table-row');
  
  $.ajax({
    url : url,
    type: 'get',
    beforeSend: (xhr) => {
      const csrfToken  = $('meta[name="_csrf"]').attr('content'),
            csrfHeader = $('meta[name="_csrf_header"]').attr('content');
      xhr.setRequestHeader(csrfHeader, csrfToken); // csrf 처리
    },
    success: (res) => {
      const data    = res.result;
      let tableHtml = '';
      data.forEach(item => {
        tableHtml += `<tr>
                        <td>${item.type}</td>
                        <td>${item.management_number}</td>
                        <td>${item.name}</td>
                        <td>${item.model_name}</td>
                        <td>${item.quantity}</td>
                        <td>${item.create_date}</td>
                        <td>${item.price}</td>
                        <td>${item.purchase}</td>
                        <td>${item.department}</td>
                        <td>${item.user}</td>
                        <td>${item.note}</td>
                        <td>
                          <button 
                            class="btn btn-light btn-sm" 
                            onclick="deleteRow(${item.seq});"
                          >
                            Delete
                          </button>
                          <button 
                            class="btn btn-light btn-sm" 
                            onclick="updateData(${item.seq});"
                          >
                            Update
                          </button>
                        </td>
                      </tr>`;
      });
      table.innerHTML = tableHtml;
    },
    error: (jqXHR, textStatus, errorThrown) => {
      alert('서버에 문제가 발생하였습니다. 다시 시도해주세요');
    }
  });
}
//=====================================================================================
function addRow() {
  const table       = document.querySelector('#addInput > table'),
        tableMargin = document.querySelector('#addInput'),
        addRow      = document.querySelectorAll('.addRow');
  
  if (addRow.length <= 0) {
    table.innerHTML += `<tr class="addRow">
                          <td>
                            <input
                              class="insertVlaue"
                              type="text" 
                              placeholder="계정과목"
                              name="type"
                            >
                          </td>
                          <td>
                            <input
                              class="insertVlaue"
                              type="text" 
                              placeholder="관리번호"
                              name="management_number"
                            >
                          </td>
                          <td>
                            <input
                              class="insertVlaue"
                              type="text" 
                              placeholder="자산명"
                              name="name"
                            >
                          </td>
                          <td>
                            <input
                              class="insertVlaue"
                              type="text" 
                              placeholder="형식(모델명)"
                              name="model_name"
                            >
                          </td>
                          <td>
                            <input
                              class="insertVlaue"
                              type="text" 
                              placeholder="수량"
                              name="quantity"
                            >
                          </td>
                          <td>
                            <input
                              class="insertVlaue"
                              type="text" 
                              placeholder="취득일자"
                              name="create_date"
                            >
                          </td>
                          <td>
                            <input
                              class="insertVlaue"
                              type="text" 
                              placeholder="취득금액"
                              name="price"
                            >
                          </td>
                          <td>
                            <input
                              class="insertVlaue"
                              type="text" 
                              placeholder="구입처"
                              name="purchase"
                            ></td>
                          <td>
                            <input
                              class="insertVlaue"
                              type="text" 
                              placeholder="사용부서"
                              name="department"
                            >
                          </td>
                          <td>
                            <input
                              class="insertVlaue"
                              type="text" 
                              placeholder="사용자"
                              name="user"
                            >
                          </td>
                          <td>
                            <input
                              class="insertVlaue"
                              type="text" 
                              placeholder="비고"
                              name="note"
                            >
                          </td>
                          <td>
                            <button 
                              class="btn btn-outline-dark btn-sm" 
                              onclick="addDivClose();"
                              style="width: 41px;"
                            >
                              취소
                            </button>
                          </td>
                          <td>
                            <button 
                              class="btn btn-outline-dark btn-sm" 
                              onclick="addDivInsert();"
                              style="width: 41px;"
                            >
                              추가
                            </button>
                          </td>
                        </tr>`;
  }
  tableMargin.style.marginBottom = '10px';
}
//=====================================================================================
function addDivClose() {
  const divTable     = document.querySelector('#addInput > table');
  divTable.innerHTML = '';
}
//=====================================================================================
function addDivInsert() {
  const divTable = document.querySelectorAll('.insertVlaue'), 
        obj      = {};
        
  divTable.forEach(table => {
    obj[table.name] = table.value;
  });
  
  $.ajax({
    url : '/insert_management',
    data: JSON.stringify(obj),
    type: 'post',
    contentType: 'application/json; charset-utf-8',
    beforeSend: (xhr) => {
      const csrfToken  = $('meta[name="_csrf"]').attr('content'),
            csrfHeader = $('meta[name="_csrf_header"]').attr('content');
      xhr.setRequestHeader(csrfHeader, csrfToken); // csrf 처리
    },
    success: (res) => {
      addDivClose();
      createTable();
      alert('저장되었습니다.');
    },
    error: (jqXHR, textStatus, errorThrown) => {
      alert('서버에 문제가 발생하였습니다. 다시 시도해주세요');
    }
  });
}
//=====================================================================================
function deleteRow(seq) {
  
  const isSelected = confirm('삭제 하시겠습니까?');
  
  (isSelected === true) ? (
    $.ajax({
      url : '/delete_management',
      type: 'post',
      data: JSON.stringify({seq}),
      contentType: 'application/json; charset-utf-8',
      beforeSend: (xhr) => {
        const csrfToken  = $('meta[name="_csrf"]').attr('content'),
              csrfHeader = $('meta[name="_csrf_header"]').attr('content');
        xhr.setRequestHeader(csrfHeader, csrfToken); // csrf 처리
      },
      success: (res) => {
        createTable();
        alert('삭제되었습니다.');
      },
      error: (jqXHR, textStatus, errorThrown) => {
        alert('서버에 문제가 발생하였습니다. 다시 시도해주세요');
      }
    })
  ) : (
    alert('삭제를 취소하셨습니다.')
  );
  
}
//=====================================================================================
function updateData(seq) {
  
  $.ajax({
    url : '/update_data_management',
    type: 'post',
    data: JSON.stringify({seq}),
    contentType: 'application/json; charset-utf-8',
    beforeSend: (xhr) => {
      const csrfToken  = $('meta[name="_csrf"]').attr('content'),
            csrfHeader = $('meta[name="_csrf_header"]').attr('content');
      xhr.setRequestHeader(csrfHeader, csrfToken); // csrf 처리
    },
    success: (res) => {
      document.querySelector('.modal-open').click();
      document.querySelector('.modal-body').innerHTML = `<table class="table-form">
                                                           <tr>
                                                             <td>계정과목</td>
                                                             <td>
                                                               <input
                                                                 class="ml-1"
                                                                 name="type" 
                                                                 value="${res.type}"
                                                               >
                                                             </td>
                                                           </tr>
                                                           <tr>
                                                             <td>관리번호</td>
                                                             <td>
                                                               <input
                                                                 class="ml-1"
                                                                 name="management_number" 
                                                                 value="${res.management_number}"
                                                               >
                                                             </td>
                                                           </tr>
                                                           <tr>
                                                             <td>자산명</td>  
                                                             <td>
                                                               <input
                                                                 class="ml-1"
                                                                 name="name" 
                                                                 value="${res.name}"
                                                               >
                                                             </td>
                                                           </tr>
                                                           <tr>
                                                             <td>형식(모델명)</td>     
                                                             <td>
                                                               <input
                                                                 class="ml-1"
                                                                 name="model_name" 
                                                                 value="${res.model_name}"
                                                               >
                                                             </td>
                                                           </tr>
                                                           <tr>
                                                             <td>수량</td>     
                                                             <td>
                                                               <input
                                                                 class="ml-1"
                                                                 name="quantity" 
                                                                 value="${res.quantity}"
                                                               >
                                                             </td>
                                                           </tr>
                                                           <tr>
                                                             <td>취득일자</td>     
                                                             <td>
                                                               <input 
                                                                 class="ml-1"
                                                                 name="create_date" 
                                                                 value="${res.create_date}"
                                                               >
                                                             </td>
                                                           </tr>
                                                           <tr>
                                                             <td>취득금액</td>     
                                                             <td>
                                                               <input
                                                                 class="ml-1"
                                                                 name="price" 
                                                                 value="${res.price}"
                                                               >
                                                             </td>
                                                           </tr>
                                                           <tr>
                                                             <td>구입처</td>   
                                                             <td>
                                                               <input
                                                                 class="ml-1" 
                                                                 name="purchase" 
                                                                 value="${res.purchase}"
                                                               ></td>
                                                           </tr>
                                                           <tr>
                                                             <td>사용부서</td>    
                                                             <td>
                                                               <input 
                                                                 class="ml-1"
                                                                 name="department" 
                                                                 value="${res.department}"
                                                               >
                                                             </td>
                                                           </tr>
                                                           <tr>
                                                             <td>사용자</td>    
                                                             <td>
                                                               <input 
                                                                 class="ml-1"
                                                                 name="user" 
                                                                 value="${res.user}"
                                                               >
                                                             </td>
                                                           </tr> 
                                                           <tr>
                                                             <td>비고</td>   
                                                             <td>
                                                               <input
                                                                 class="ml-1"
                                                                 name="note" 
                                                                 value="${res.note}"
                                                               >
                                                             </td>
                                                             <td class="d-none">
                                                               <input
                                                                 class="ml-1"
                                                                 name="seq"
                                                                 type="hidden"
                                                                 value="${res.seq}"
                                                               >
                                                             </td>
                                                           </tr>
                                                         </tabel>`;
    },
    error: (jqXHR, textStatus, errorThrown) => {
      alert('서버에 문제가 발생하였습니다. 다시 시도해주세요');
    }
  });
}
//=====================================================================================
function updateRow() {
  
  const obj = {
    seq: document.querySelector('input[name=seq]').value,
    type: document.querySelector('input[name=type]').value,
    management_number: document.querySelector('input[name=management_number]').value,
    name: document.querySelector('input[name=name]').value,
    model_name: document.querySelector('input[name=model_name]').value,
    quantity: document.querySelector('input[name=quantity]').value,
    create_date: document.querySelector('input[name=create_date]').value,
    price: document.querySelector('input[name=price]').value,
    purchase: document.querySelector('input[name=purchase]').value,
    department: document.querySelector('input[name=department]').value,
    user: document.querySelector('input[name=user]').value,
    note: document.querySelector('input[name=note]').value
  }
  
  $.ajax({
    url : '/update_management',
    data: JSON.stringify(obj),
    type: 'post',
    contentType: 'application/json; charset-utf-8',
    beforeSend: (xhr) => {
      const csrfToken  = $('meta[name="_csrf"]').attr('content'),
            csrfHeader = $('meta[name="_csrf_header"]').attr('content');
      xhr.setRequestHeader(csrfHeader, csrfToken); // csrf 처리
    },
    success: (res) => {
      document.querySelector('.close-btn').click();
      createTable();
      alert('업데이트 되었습니다.');
    },
    error: (jqXHR, textStatus, errorThrown) => {
      alert('서버에 문제가 발생하였습니다. 다시 시도해주세요');
    }
  });
}
//=====================================================================================
function searchName() {
  const name = document.querySelector('#searchInput').value;
  return createTable(name);
}
//=====================================================================================
function s2ab(s) { 
  const buf  = new ArrayBuffer(s.length); // convert s to arrayBuffer
  const view = new Uint8Array(buf);  // create uint8array as viewer
  
  for (var i = 0; i < s.length; i++) view[i] = s.charCodeAt(i) & 0xFF; // convert to octet
  
  return buf;    
}
//=====================================================================================
function exportExcel() {
  
  const excelHandler = {
    getExcelFileName: () => {
      return '자산관리대장(장비).xlsx'; // file name
    },
    getSheetName: () => {
      return '장비';  // sheet name
    },
    getExcelData: () => {
      return document.getElementById('tableData');  // table id
    },
    getWorksheet: () => {
      return XLSX.utils.table_to_sheet(this.getExcelData());
    }
  };
  
  // step 1. workbook 생성
  const wb = XLSX.utils.book_new();
  // step 2. 시트 만들기 
  const newWorksheet = excelHandler.getWorksheet();
  // step 3. workbook에 새로만든 워크시트에 이름을 주고 붙인다.  
  XLSX.utils.book_append_sheet(wb, newWorksheet, excelHandler.getSheetName());
  // step 4. 엑셀 파일 만들기 
  const wbout = XLSX.write(wb, { bookType: 'xlsx',  type: 'binary' });
  // step 5. 엑셀 파일 내보내기 
  saveAs(new Blob([s2ab(wbout)],{ type: 'application/octet-stream' }), excelHandler.getExcelFileName());
}
//=====================================================================================
```
4. java
```
service 없이 controller에 로직 구현
```
```java
@Slf4j
@RestController
@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
public class MainRestController extends SuperController {

	@Autowired
	MainMapper	mainMapper;
	
	@GetMapping(value = "/select_management")
  public HashMap<String, Object> selectManagement(HttpSession httpSession, @RequestParam(defaultValue = "check") String name) throws Exception {
    log.info(">>> MainRestController selectManagement >>>");
    
    // service impl이 없으니 controller단에서 service logic 처리
    HashMap<String, Object> result = new HashMap<String, Object>();
    
    if (name.equals("check")) {
      result.put("result", mainMapper.selectManagement());
    } else {
      result.put("result", mainMapper.searchManagement(name));
    }
    
    return result;
  }
	
	@PostMapping(value = "/delete_management")
  public HashMap<String, Object> deleteManagement(HttpSession httpSession, @RequestBody HashMap<String, Object> params, MainDO mainDo) throws Exception {
    log.info(">>> MainRestController deleteManagement >>>");

    {
      mainDo.setSeq(Integer.parseInt(String.valueOf(params.get("seq"))));
    }
   
    HashMap<String, Object> result = new HashMap<String, Object>();
    result.put("result", mainMapper.deleteManagement(mainDo));
    
    return result;
  }

	@PostMapping(value = "/insert_management")
	public int insertManagement(HttpSession httpSession, @RequestBody HashMap<String, Object> params, MainDO mainDo) throws Exception {
		log.info(">>> MainRestController insertManagement >>>");

		{
	    mainDo.setType((String) params.get("type"));
	    mainDo.setManagement_number((String) params.get("management_number"));
	    mainDo.setName((String) params.get("name"));
	    mainDo.setModel_name((String) params.get("model_name"));
	    mainDo.setQuantity((String) params.get("quantity"));
	    mainDo.setCreate_date((String) params.get("create_date"));
	    mainDo.setPrice((String) params.get("price"));
	    mainDo.setPurchase((String) params.get("purchase"));
	    mainDo.setDepartment((String) params.get("department"));
	    mainDo.setUser((String) params.get("user"));
	    mainDo.setNote((String) params.get("note"));
	    mainDo.setDescription((String) params.get("description"));
	    mainDo.setUpdate_date((String) params.get("update_date"));
	    mainDo.setCompany_name((String) params.get("company_name"));
		}
		return mainMapper.insertManagement(mainDo);
	}
	
  @PostMapping(value = "/update_data_management")
	public MainDO updateDataManagement(HttpSession httpSession, @RequestBody HashMap<String, Object> params, MainDO mainDo) throws Exception {
	  log.info(">>> MainRestController updateDataManagement >>>");
	    
    {
      mainDo.setSeq(Integer.parseInt(String.valueOf(params.get("seq"))));
	  }
	  mainDo = mainMapper.updateDataManagement(mainDo);
	    
	  return mainDo;
  }
	 
  @PostMapping(value = "/update_management")
  public int updateManagement(HttpSession httpSession, @RequestBody HashMap<String, Object> params, MainDO mainDo) throws Exception {
    log.info(">>> MainRestController updateManagement >>>");
     
    log.info(">>> params => {} >>>", params);
    
    {
      mainDo.setSeq(Integer.parseInt(String.valueOf(params.get("seq"))));
      mainDo.setType((String) params.get("type"));
      mainDo.setManagement_number((String) params.get("management_number"));
      mainDo.setName((String) params.get("name"));
      mainDo.setModel_name((String) params.get("model_name"));
      mainDo.setQuantity((String) params.get("quantity"));
      mainDo.setCreate_date((String) params.get("create_date"));
      mainDo.setPrice((String) params.get("price"));
      mainDo.setPurchase((String) params.get("purchase"));
      mainDo.setDepartment((String) params.get("department"));
      mainDo.setUser((String) params.get("user"));
      mainDo.setNote((String) params.get("note"));
      mainDo.setDescription((String) params.get("description"));
      mainDo.setUpdate_date((String) params.get("update_date"));
      mainDo.setCompany_name((String) params.get("company_name"));
    }
    int result = mainMapper.updateManagement(mainDo);
     
    return result;
  }  
}
```
```java
@Mapper
public interface MainMapper {
  
  public List<MainDO> selectManagement() throws Exception;
  
  public List<MainDO> searchManagement(String param) throws Exception;
  
  public int insertManagement(MainDO mainDO) throws Exception;
  
  public int deleteManagement(MainDO mainDO) throws Exception;
  
  public int updateManagement(MainDO mainDO) throws Exception;
  
  public MainDO updateDataManagement(MainDO mainDO) throws Exception;

}
```
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="kr.co.company.mapper.admin.main.MainMapper">

  <select id="selectManagement" resultType="MainDO">
    SELECT *
    FROM   management
    ORDER BY seq DESC
  </select>
  
  <select id="searchManagement" parameterType="string" resultType="MainDO">
    SELECT *
    FROM   management
    WHERE  user LIKE CONCAT('%',#{param},'%')
    ORDER BY seq DESC
  </select>
  
  <select id="updateDataManagement" parameterType="MainDO" resultType="MainDO">
    SELECT *
    FROM   management
    WHERE  seq = #{seq}
  </select>

  <delete id="deleteManagement" parameterType="MainDO">
    DELETE 
    FROM  management
    WHERE seq = #{seq}
  </delete>
  
  <update id="updateManagement" parameterType="MainDO">
    UPDATE 
    management SET type = #{type}
                   , management_number = #{management_number}
                   , name = #{name}
                   , model_name = #{model_name}
                   , quantity = #{quantity}
                   , create_date = #{create_date}
                   , price = #{price}
                   , purchase = #{purchase}
                   , department = #{department}
                   , user = #{user}
                   , note = #{note}
                   , description = #{description}
                   , update_date = #{update_date} 
                   , company_name = #{company_name} 
    WHERE seq = #{seq}
  </update>
  
	<insert id="insertManagement" parameterType="MainDO">
    INSERT INTO management(
                           type
                           , management_number
                           , name
                           , model_name
                           , quantity
                           , create_date
                           , price
                           , purchase
                           , department
                           , user
                           , note
                           , description
                           , update_date
                           , company_name
                          )
    VALUES      (
                 #{type}
                 , #{management_number}
                 , #{name}
                 , #{model_name}
                 , #{quantity}
                 , #{create_date}
                 , #{price}
                 , #{purchase}
                 , #{department}
                 , #{user}
                 , #{note}
                 , #{description}
                 , #{update_date} 
                 , #{company_name} 
                )
	</insert>

</mapper>
```
```java
@Data
public class MainDO implements Serializable {

    private static final long serialVersionUID = 862665384846538499L;

    private int seq;
    private String type;
    private String management_number;
    private String name;
    private String model_name;
    private String quantity;
    private String create_date;
    private String price;
    private String purchase;
    private String department;
    private String user;
    private String note;
    private String description;
    private String update_date;
    private String company_name;

}
```
