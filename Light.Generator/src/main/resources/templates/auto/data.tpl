<!DOCTYPE html>
<html>
<meta charset="utf-8">
<head>
	<style>
		table {
			border-collapse: collapse;
			width: 100%;
			font-size: 14px;
		}

		th,
		td {
			text-align: left;
			padding: 8px;
		}

		tr:nth-child(even) {
			background-color: #f2f2f2
		}

		th {
			color: #333;
		}

		h2 {
			margin: 10px;
			font-size: 20px;
		}
		.title{
			max-width: 1000px;
			margin: 20px auto;
		}

		.content {
			border: 1px solid #eee; margin: 20px;
			border-radius: 6px;
			max-width: 1000px;
			margin: 20px auto;
			box-shadow: 0 2px 4px 0 rgb(0 0 0 / 10%);
		}
	</style>
</head>

<body>
<div class="title"><h1>项目数据字典 </h1> <h2>#(dateTime)</h2></div>
#for(dictData: dictionaryList)
    <div class="content">
        <h2>#(dictData.tableName) (#(dictData.tableInfo))</h2>
        <table>
            <tr><th>名称</th><th>数据类型</th> <th>长度</th><th>允许空值</th><th>说明</th></tr>
            #for(f: dictData.items)
                <tr><td>#(f.fieldName)</td><td>#(f.fieldType)</td> <td>#(f.fieldLen)</td><td>#(f.fieldNull)</td><td>#(f.fieldInfo)</td></tr>
            #end
        </table>
    </div>
#end
</body>

</html>