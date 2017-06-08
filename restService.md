> 以下api都是GET

/api/movie
```javascript
[
    {
        "id":123,
        "name":"三只小猪",
        "score":7.0,
        "img":"http://xxx.jpg"
    },
]
```

/api/movie/{id}
```
{
    "id":123,
    "name":"三只小猪",
    "score":7.0,
    "img":"http://xxx.jpg"
}
```

/api/movie/{id}/date
```javascript
[
    "2017-06-09",
    "2017-06-10"
]
```

/api/movie/{id}/date/{yyyy:MM:dd}
```
{
     "id":123,
     "name":"三只小猪",
     "date":"2017-06-09",
     "screenings":[
        {
            "cinema":"蓝海影幸福城江宁店",
            "time":"11:20",
            "price":39,
            "remain":"50%", //不一定有
            "type":"英语3D" //不一定有
        },
     ]
}
```