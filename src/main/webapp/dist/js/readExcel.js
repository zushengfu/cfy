/**
 * Created by Administrator on 2017/9/29.
 */
/**
 *
 * @param file：一个文件对象
 * note: 依赖于layer.js,xlsx.full.min.js,如需兼容低版本浏览器，需要在这个js之前加上
 * shim.js
 */
function readExcel ( file, callback ) {
	var fileReader = new FileReader();

	fileReader.readAsBinaryString(file);
	fileReader.onload = function() {

		try {
			//获取读取的excel数据
			var data = this.result,

			//以二进制流方式读取整个excel表格对象
				workbook = XLSX.read(data, {
					type: 'binary'
				}),
				persons = [];
		} catch (e) {
			comm.layer.alert("读取错误，文件类型不正确！")
			return;
		}

		// 表格的表格范围，可用于判断表头数量是否正确
		var fromTo = '';

		// 遍历每张表读取
		for (var sheet in workbook.Sheets) {
			if (workbook.Sheets.hasOwnProperty(sheet)) {
				fromTo = workbook.Sheets[sheet]['!ref'];
				persons = persons.concat(XLSX.utils.sheet_to_json(workbook.Sheets[sheet]));
				//break; // 如果只取第一张表，就取消注释这行
			}
		}
		if(!callback){
			throw new Error('callback参数不能为空！');
		}
		callback && callback(persons);
	}
}