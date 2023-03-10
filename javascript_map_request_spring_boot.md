# javascript에서 axios(post) map으로 request후 spring으로 받아보자!

```javascript
function sendMap(map) {
	(async () => {
		try {
			const result = await axios.post('/mapInsert', Object.fromEntries(map));
			if (result.status === 200) alert('success');

		} catch (err) {
			console.log('error => ', err);
		}
	})();
}
```

```java
@PostMapping(value = "/mapInsert")
public void mapInsert(Model model,
    @RequestBody Map<String, Object> params) throws Exception {
  log.info(">>> MapRestController mapInsert start >>>");
  log.info(">>> map => {} >>>", params);
}
```