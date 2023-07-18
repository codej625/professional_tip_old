# 실시간으로 변하는 board를 만들어보자!

1. front
```html
<div th:fragment="frag_body" class="col-md-12">
  <!-- Page Heading -->
  <nav aria-label="breadcrumb">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"></li>
      <li class="breadcrumb-item"></li>
      <li class="breadcrumb-item active" aria-current="page"></li>
    </ol>
  </nav>
  <div class="card shadow mb-4">
    <div class="card-body">
      <div class="div-button">
        <div>
          <form id="date">
            <input 
              id="dateStart" 
              class="form-control ml-1" 
              name="dateStart" 
              type="date" 
              value=""
              onchange="ajaxReload();"
            >
            <input 
              id="dateEnd"
              class="form-control ml-1" 
              name="dateEnd"   
              type="date" 
              value=""
              onchange="ajaxReload();"
            >
          </form>
        </div>
        <div>
          <button 
            id="excel-down" 
            class="btn btn-outline-secondary ml-1" 
            type="button"
          >
            엑셀 다운로드
          </button>
          <button 
            id="refresh"    
            class="btn btn-outline-secondary"      
            type="button"
          >
            새로 고침
          </button>
        </div>
      </div>
      <div class="table-responsive">
        <table class="table table-hover table-bordered">
          <thead>
            <tr>
              <th>
                <input 
                  class="text-center rounded" 
                  value="no"
                >
              </th>
              <th>
                <input 
                  class="text-center rounded" 
                  value="req_date"
                >
              </th>
              <th>
                <input 
                  class="text-center rounded" 
                  value="time"
                >
              </th>
              <th class="ip">
                <select class="rounded text-center" onchange="ajaxReload();">
                  <option class="text-center">ip</option>
                </select>
              </th>
              <th class="device" style="width: 80px;">
                <select class="rounded text-center" onchange="ajaxReload();">
                  <option class="text-center">device</option>
                </select>
              </th>
              <th class="source-code">
                <select class="rounded text-center" onchange="ajaxReload();">
                  <option class="text-center">source_code</option>
                </select>
              </th>
              <th class="term-code">
                <select class="rounded text-center" onchange="ajaxReload();">
                  <option class="text-center">term_code</option>
                </select>
              </th>
              <th class="adv-object-name">
                <select class="rounded text-center" onchange="ajaxReload();">
                  <option class="text-center">adv_object_name</option>
                </select>
              </th>
              <th>
                <input 
                  class="text-center rounded" 
                  value="광고대상1"
                >
              </th>
              <th class="contents-code">
                <select class="rounded text-center" onchange="ajaxReload();"> 
                  <option class="text-center">contents_code</option>
                </select>
              </th>
              <th class="conversion-type">
                <select class="rounded text-center" onchange="ajaxReload();"> 
                  <option class="text-center">conversion_type</option>
                </select>
              </th>
              <th>
                <input 
                  class="text-center rounded" 
                  value="duplicate_flag"
                >
              </th>
              <th>
                <input 
                  class="text-center rounded" 
                  value="insu_price"
                >
              </th>
              <th>
                <input 
                  class="text-center rounded" 
                  value="mktagreyn"
                >
              </th>
            </tr>
          </thead>
        </table>
        <table id="tb-1" class="table table-hover table-bordered">
          <thead>
            <tr>
              <th>no</th>
              <th>req_date</th>
              <th>time</th>
              <th>ip</th>
              <th>device</th>
              <th>source_code</th>
              <th>term_code</th>
              <th>adv_object_name</th>
              <th>광고대상1</th>
              <th>contents_code</th>
              <th>conversion_type</th>
              <th>duplicate_flag</th>
              <th>insu_price</th>
              <th>mktagreyn</tr>
          </thead>
          <tbody id="tbl"></tbody>
        </table>
      </div>
    </div>
  </div>
</div>
```

```javascript
// Global Variable =====================================================================
const date = new Date();
const day  = timestamp(date);
// IIFE ================================================================================
(() => {
  const startDay = $('#dateStart').val();
  const endDay = $('#dateEnd').val();

  if (startDay == '' && endDay == '') {
    $('#dateStart').val(day);
    $('#dateEnd').val(day);
  }

  return ajaxReload();
})();
// Function ============================================================================
function ajaxReload() {
  const result = ajaxLoad();
  
  return visitTable(result); /* table data */
}
// Function ============================================================================
function ajaxLoad() {
  const url = '주소를 넣는다.';
  /* select의 값들을 가져옴 */
  const ip = document.querySelector('.ip > select');
  const device = document.querySelector('.device > select');
  const sourceCode = document.querySelector('.source-code > select');
  const termCode = document.querySelector('.term-code > select');
  const advObjectName = document.querySelector('.adv-object-name > select');
  const contentsCode = document.querySelector('.contents-code > select');
  const conversionType = document.querySelector('.conversion-type > select');
  
  /* select의 값들을 객체화 한다. */
  const formDatas = {
    dateStart: document.querySelector('#dateStart').value,
    dateEnd: document.querySelector('#dateEnd').value,
    ip: (ip.value === 'ip') ? '' : ip.value,
    device: (device.value === 'device') ? '' : device.value,
    sourceCode: (sourceCode.value === 'source_code') ? '' : sourceCode.value,
    termCode: (termCode.value === 'term_code') ? '' : termCode.value,
    advObjectName: (advObjectName.value === 'adv_object_name') ? '' : advObjectName.value,
    contentsCode: (contentsCode.value === 'contents_code') ? '' : contentsCode.value,
    conversionType: (conversionType.value === 'conversion_type') ? '' : conversionType.value
  };
  
  /* ajax api request */
  let result;
  
  $.ajax({ /* axios로 소스 교체 가능 */
    url: url,
    data: formDatas,
    type: 'POST',
    async: false, /* 동기처리 */
    beforeSend: xmlHttpRequest => {
      const csrfToken  = $('meta[name="_csrf"]').attr('content');
      const csrfHeader = $('meta[name="_csrf_header"]').attr('content');
      xmlHttpRequest.setRequestHeader(csrfHeader, csrfToken); /* csrf 처리 */
    },
    success: res => {
      result = res;
    },
    error: (jqXHR, textStatus, errorThrown) => {
      alert('서버에 문제가 발생하였습니다. 다시 시도해주세요');
      console.log('jqXHR, textStatus, errorThrown => ', `${jqXHR}, ${textStatus}, ${errorThrown}`);
    }
  });
  
  return result;
};
// Function ============================================================================
function visitTable(datas) {
  const documents = document.createDocumentFragment(); /* dom 최적화 */
  const innerTable = datas[7];
  
  /* select의 값들을 가져옴 */
  const ip = document.querySelector('.ip > select');
  const device = document.querySelector('.device > select');
  const sourceCode = document.querySelector('.source-code > select');
  const termCode = document.querySelector('.term-code > select');
  const advObjectName = document.querySelector('.adv-object-name > select');
  const contentsCode = document.querySelector('.contents-code > select');
  const conversionType = document.querySelector('.conversion-type > select');
  
  /* create select */
  for (let i = 0; i < datas.length-1; i++) {
    let option = [];
    
    datas[i].forEach(element => {
      switch (i) {
        case 0: 
          option.push((ip.value === 'ip') ? (
            '<option value="ip">ip</option>'
          ) : (
            `<option value="${ip.value}">${ip.value}</option>`
          )); 
          break;
        case 1: 
          option.push((device.value === 'device') ? (
            '<option value="device">device</option>'
          ) : (
            `<option value="${device.value}">${device.value}</option>`
          )); 
          break;
        case 2: 
          option.push((sourceCode.value === 'source_code') ? (
            '<option value="source_code">source_code</option>'
          ) : (
            `<option value="${sourceCode.value}">${sourceCode.value}</option>`
          )); 
          break;
        case 3: 
          option.push((termCode.value === 'term_code') ? (
            '<option value="term_code">term_code</option>'
          ) : (
            `<option value="${termCode.value}">${termCode.value}</option>`
          )); 
          break;
        case 4: 
          option.push((advObjectName.value === 'adv_object_name') ? (
            '<option value="adv_object_name">adv_object_name</option>'
          ) : (
            `<option value="${advObjectName.value}">${advObjectName.value}</option>`
          ));
          break;
        case 5: 
          option.push((contentsCode.value === 'contents_code') ? (
            '<option value="contents_code">contents_code</option>'
          ) : (
            `<option value="${contentsCode.value}">${contentsCode.value}</option>`
          )); 
          break;
        case 6: 
          option.push((conversionType.value === 'conversion_type') ? (
            '<option value="conversion_type">conversion_type</option>'
          ) : (
            `<option value="${conversionType.value}">${conversionType.value}</option>`
          )); 
          break;
      
        default: console.log('error'); break;
      }
      
      option.push(`<option value="${element}">${element}</option>`);
    });
    
    option = Array.from(new Set(option));
    
    switch (i) {
      case 0: ip.innerHTML = option; break;
      case 1: device.innerHTML = option; break;
      case 2: sourceCode.innerHTML = option; break;
      case 3: termCode.innerHTML = option; break;
      case 4: advObjectName.innerHTML = option; break;
      case 5: contentsCode.innerHTML = option; break;
      case 6: conversionType.innerHTML = option; break;
      
      default: console.log('error');
    }
  }

  /* create table */
  for (let i = 0; i < innerTable.length; i++) {
    let td;
    const tr = document.createElement('tr');
    const seq  = innerTable.length - i;
    
    /* no */
    td = document.createElement('td');
    td.textContent = seq;
    tr.appendChild(td);
    /* date */
    td = document.createElement('td');
    td.textContent = timestamp(innerTable[i].req_time);
    tr.appendChild(td);
    /* time */
    td = document.createElement('td');
    td.textContent = timestamp_second(innerTable[i].req_time);
    tr.appendChild(td);
    /* ip */
    td = document.createElement('td');
    td.textContent = innerTable[i].ip;
    tr.appendChild(td);
    /* device */
    td = document.createElement('td');
    td.textContent = device_change(innerTable[i].device);
    tr.appendChild(td);
    /* source code */
    td = document.createElement('td');
    td.textContent = setData(innerTable[i].source_code);
    tr.appendChild(td);
    /* term code */
    td = document.createElement('td');
    td.textContent = setData(innerTable[i].term_code);
    tr.appendChild(td);
    /* adv object name */
    td = document.createElement('td');
    td.textContent = setData(innerTable[i].adv_object_name);
    tr.appendChild(td);
    /* campaign */
    td = document.createElement('td');
    td.textContent = advObject(innerTable[i].adv_object_name);
    tr.appendChild(td);
    /* contents code */
    td = document.createElement('td');
    td.textContent = setData(innerTable[i].contents_code);
    tr.appendChild(td);
    /* conversion type */
    td = document.createElement('td');
    td.textContent = setData(innerTable[i].conversion_type);
    tr.appendChild(td);
    /* duplicate flag */
    td = document.createElement('td');
    td.textContent = setData(innerTable[i].duplicate_flag);
    tr.appendChild(td);
    /* insu price */
    td = document.createElement('td');
    td.textContent = setData(innerTable[i].insu_price);
    tr.appendChild(td);
    /* mktagreyn */
    td = document.createElement('td');
    td.textContent = setData(innerTable[i].mktagreyn);
    tr.appendChild(td);
    
    documents.appendChild(tr);
  }
  
  return $('#tbl').html(documents);
}
// Function ============================================================================
function timestamp(_date) {
  const date = new Date(_date);
  const day = ("0" + date.getDate()).slice(-2);
  const month = ("0" + (date.getMonth() + 1)).slice(-2);
  const year = date.getFullYear();
  const result = `${year}-${month}-${day}`;
  
  return result;  
}
// Function ============================================================================
function timestamp_second(_date) {
  const date = new Date(_date);
  const hours = ('0' + date.getHours()).slice(-2); 
  const minutes = ('0' + date.getMinutes()).slice(-2);
  const result = `${hours}:${minutes}`;
  
  return result;
}
// Function ============================================================================
function device_change(data) {
  let result;
  
  (data === 'pc') ? (result = 'PC') : (result = 'MO');
  
  return result;
}
// Function ============================================================================
function setData(data) {
  if (data == null)
    return '';
  else if (data == undefined)
    return '';
  else if (data == '')
    return '';
  else
    return data;
}
// Function ============================================================================
function fnTbInfoExcel() { /* html table => excel */
  const fileName = day + ' .xlsx';
  const tableName = document.getElementById('tb-1');
  const workBook = XLSX.utils.table_to_book(tableName, { sheet: '' });
  
  XLSX.writeFile(workBook, fileName, { bookType:'xlsx',  type: 'binary' });
}
// Function ============================================================================
$('#excel-down').on('click', function() {
  return fnTbInfoExcel();
});
// Function ============================================================================
$('#refresh').on('click', function() {
  window.location.reload();
});
// Function ============================================================================
function advObject(data) {
  let result;
  
  switch (data) {
    case '건강 보장': result = '건강보험'; break;
    case '보장 분석': result = '기타'; break;
    case '주택 화재': result = '주택보험'; break;
    case '안심 종합': result = '안심보험'; break;
    
    default: console.log('error'); break;
  }
  
  return result;
}
//======================================================================================
```

2. backend

```java
@RequestMapping(value = "/", produces = "application/json; charset=utf-8")
public String test(HttpSession httpSession,
                  @RequestParam
                  HashMap<String, Object> param) throws Exception {

  log.info(" >> controller start >> ");

  /* request parameter */
  String device = (String) param.get("device");
  String sourceCode = (String) param.get("sourceCode");
  String termCode  = (String) param.get("termCode");
  String advObjectName = (String) param.get("advObjectName");
  String ip = (String) param.get("ip");
  String contentsCode  = (String) param.get("contentsCode");
  String conversionType = (String) param.get("conversionType");
  
  /* date parameter */
  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  String dateStart = param.get("dateStart") + " 00:00:00";
  String dateEnd = param.get("dateEnd") + " 23:59:59";
  Date dateStartSDF = simpleDateFormat.parse(dateStart);
  Date dateEndSDF = simpleDateFormat.parse(dateEnd);
  long dateStartL = dateStartSDF.getTime();
  long dateEndL = dateEndSDF.getTime();
  
  /* mybatis parameter */
  HashMap<String, Object> parameter = new HashMap<String, Object>();
  parameter.put("dateStart", dateStartL);
  parameter.put("dateEnd", dateEndL);
  parameter.put("device", device);
  parameter.put("sourceCode", sourceCode);
  parameter.put("termCode", termCode);
  parameter.put("advObjectName", advObjectName);
  parameter.put("ip", ip);
  parameter.put("contentsCode", contentsCode);
  parameter.put("conversionType", conversionType);
  
  /* DB return value */
  List<Map<String, Object>> tableRow = mapper.getLotte(parameter);
  
  List<Object> result = new ArrayList<Object>();
  /* select value */
  result.add(mapper.getIpList(parameter)); // index 0
  result.add(mapper.getDeviceList(parameter)); // index 1
  result.add(mapper.getSourceCodeList(parameter)); // index 2
  result.add(mapper.getTermCodeList(parameter)); // index 3
  result.add(mapper.getAdvObjectNameList(parameter)); // index 4
  result.add(mapper.getContentsCodeList(parameter)); // index 5
  result.add(mapper.getConversionTypeList(parameter)); // index 6
  
  /* table value */
  result.add(tableRow); // index 7
  
  return new ObjectMapper().writeValueAsString(result);
}
```

```sql
<select id="" parameterType="hashmap" resultType="hashmap">
  SELECT *
  FROM 
  WHERE req_time BETWEEN #{dateStart} AND #{dateEnd}
  <if test="device != '' and device != null">
    AND device = #{device}
  </if>
  <if test="sourceCode != '' and sourceCode != null">
    AND source_code = #{sourceCode}
  </if>
  <if test="termCode != '' and termCode != null">
    AND term_code = #{termCode}
  </if>
  <if test="advObjectName != '' and advObjectName != null">
    AND adv_object_name = #{advObjectName}
  </if>
  <if test="ip != '' and ip != null">
    AND ip = #{ip}
  </if>
  <if test="contentsCode != '' and contentsCode != null">
    AND contents_code = #{contentsCode}
  </if>
  <if test="conversionType != '' and conversionType != null">
    AND conversion_type = #{conversionType}
  </if>
  ORDER BY id DESC
</select>

<select id="getDeviceList" parameterType="hashmap" resultType="String">
  SELECT device
    FROM 
    WHERE device IS NOT NULL 
      AND device != '' 
      AND req_time BETWEEN #{dateStart} AND #{dateEnd}
  GROUP BY device
</select>

<select id="getSourceCodeList" parameterType="hashmap" resultType="String">
  SELECT source_code
    FROM 
    WHERE source_code IS NOT NULL 
      AND source_code != '' 
      AND req_time BETWEEN #{dateStart} AND #{dateEnd}
  GROUP BY source_code
</select>

<select id="getTermCodeList" parameterType="hashmap" resultType="String">
  SELECT term_code
    FROM 
    WHERE term_code IS NOT NULL 
      AND term_code != '' 
      AND req_time BETWEEN #{dateStart} AND #{dateEnd}
  GROUP BY term_code
</select>

<select id="getAdvObjectNameList" parameterType="hashmap" resultType="String">
  SELECT adv_object_name
    FROM 
    WHERE adv_object_name IS NOT NULL 
      AND adv_object_name != '' 
      AND req_time BETWEEN #{dateStart} AND #{dateEnd}
  GROUP BY adv_object_name
</select>

<select id="getIpList" parameterType="hashmap" resultType="String">
  SELECT ip
    FROM 
    WHERE ip IS NOT NULL 
      AND ip != '' 
      AND req_time BETWEEN #{dateStart} AND #{dateEnd}
  GROUP BY ip
</select>

<select id="getContentsCodeList" parameterType="hashmap" resultType="String">
  SELECT contents_code
    FROM 
    WHERE contents_code IS NOT NULL 
      AND contents_code != '' 
      AND req_time BETWEEN #{dateStart} AND #{dateEnd}
  GROUP BY contents_code
</select>

<select id="getConversionTypeList" parameterType="hashmap" resultType="String">
  SELECT conversion_type
    FROM 
    WHERE conversion_type IS NOT NULL 
      AND conversion_type != '' 
      AND req_time BETWEEN #{dateStart} AND #{dateEnd}
  GROUP BY conversion_type
</select>
```