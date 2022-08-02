# chart.js 누적 막대 그래프 만들기 (+ 데이터 변수 적용)

## 변수화 시킨 data

```javascript
const ctx = $('#test-chart');
const year2021 = {
    label: '2021',
    data: [10,8,6,5,12,7],
    backgroundColor: 'rgba(203,206,145,.5)',
    borderColor: '#CBCE91',
    borderWidth: 1
}
const year2020 = {
    label: '2020',
    data: [5,10,5,3,4,2],
    backgroundColor: 'rgba(203,206,145,.5)',
    borderColor: '#CBCE91',
    borderWidth: 1
}
const data = {
    labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
    datasets: [
        year2021,
        year2020
    ]
}
const options = {
    maintainAspectRatio: false,//그래프의 비율 유지
    scales: {
        x: { //x축값 누적
            stacked:true
        },
        x: { //y축값 누적
            stacked:true
        },
    }
}

```
## 변수로 대입
```javascript
const myChart = new Chart(ctx, {
    type: 'bar',
    data: data,
    options: options
});
```

```html
<div class="chart-wrap">
    <canvas id=test-chart></canvas>
</div>
```
