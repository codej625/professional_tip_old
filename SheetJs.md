# cdn 추가
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/1.3.8/FileSaver.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.15.6/xlsx.full.min.js"></script>

## ex table이 3개이고 excel에는 3개의 sheet으로 save하려고 한다면

```javascript
function fnTbInfo_Excel() { 
	//XLSX.utils.book_new() 를 이용해서 새 통합 문서 객체 생성 
    workBook = XLSX.utils.book_new(); 
    
    //테이블을 시트로 변환 (table_to_sheet 이용)
    //워크북을 시트로 변환 (book_append_sheet 이용) 
    XLSX.utils.book_append_sheet(workBook , XLSX.utils.table_to_sheet(document.getElementById('tb1')), "tb1NAME"); 
    XLSX.utils.book_append_sheet(workBook , XLSX.utils.table_to_sheet(document.getElementById('tb2')), "tb2NAME"); 
    XLSX.utils.book_append_sheet(workBook , XLSX.utils.table_to_sheet(document.getElementById('tb3')), "tb3NAME"); 
    
    XLSX.writeFile(workBook, 'excelTest.xlsx', { bookType: 'xlsx', type: 'binary' }); 
  }
  ```