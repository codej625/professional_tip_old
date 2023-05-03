# axios get 방식으로 parameter를 spring controller로 전송해보자!

```javascript
function createButton() {
	let addSeq = '';
	const row = document.querySelectorAll('.row');
	
	row.forEach(item => {
		const check = item.querySelector('.input').checked;
		const seq = item.querySelector('.seq').value;
		
		if (check === true) {
			addSeq += `${seq},`;
		}
	});
	
	addSeq = addSeq.substring(0, addSeq.length-1);
	
	const sendSeq = async () => {
		try {
			const result = await axios.get(`/url?seq=${addSeq}`);
		} 
		catch {
			console.log('err => ', err);
		}
	}
	
	return sendSeq();
}
```

```java
@GetMapping(value = "/url")
	public void axiosGetSendData(Model model, 
			@RequestParam(value = "seq", required = false) String seq) throws Exception {
		
		log.info(seq);

	}
```