# front단에서 객체를 여러개 보내고 spring controller단에서 여러개 객체를 받아보자!

```javascript
async function moveTable(obj1, obj2) {
	try {
		const url = '/';
		const config = {
    	headers: {
      	'Content-Type': 'application/json'
    	}
  	};
  	const data = JSON.stringify({
	    obj1,
    	obj2
    });
		const result = await axios.post(url, data, config);
		if (result.status === 200) {
			alert('import success');
    // const {obj1, obj2} = await result.data;
		}
	} catch (err) {
		console.log('error => ', err);
    // ... Logic
	}
}
```

```java
@PostMapping("/")
public ResponseEntity<String> receiveArrays(@RequestBody Map<String, List<Map<String, Object>>> obj) {
  List<Map<String, Object>> obj1 = obj.get("obj1");
  List<Map<String, Object>> obj2 = obj.get("obj2");
  
  // 수신된 배열을 콘솔에 출력
  System.out.println("obj1: " + obj1);
  System.out.println("obj2: " + obj2);
  
  return ResponseEntity.ok("obj received successfully.");
}
```