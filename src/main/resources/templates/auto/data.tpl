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
<div class="title"><h1>项目数据字典 </h1> <h2>{{dateTime}}</h2></div>
  #{tableDiv}#
</body>

</html>