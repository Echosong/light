import * as XLSX from 'xlsx'
import { parseTime } from '.';

/*
    * @description:
    * @param {Object} json 服务端发过来的数据
    * @param {String} name 导出Excel文件名字
   
    * @param {String} title 导出Excel表头
   
    * @param {String} sheetName 导出sheetName名字
    * @return:
    */
export function exportExcel(json, name, title, sheetName) {
  /* convert state to workbook */
  var data = new Array();
  var keyArray = new Array();
  const getLength = function (obj) {
    var count = 0;
    for (var i in obj) {
      if (obj.hasOwnProperty(i)) {
        count++;
      }
    }
    return count;
  };
  for (const key1 in json) {
    if (json.hasOwnProperty(key1)) {
      const element = json[key1];
      var rowDataArray = new Array();
      for (const key2 in element) {
        if (element.hasOwnProperty(key2)) {
          const element2 = element[key2];
          rowDataArray.push(element2);
          if (keyArray.length < getLength(element)) {
            keyArray.push(key2);
          }
        }
      }
      data.push(rowDataArray);
    }
  }
  // keyArray为英文字段表头
  data.splice(0, 0, keyArray, ExcelTitle[title]);
  const ws = XLSX.utils.aoa_to_sheet(data);
  const wb = XLSX.utils.book_new();
  // 此处隐藏英文字段表头
  var wsrows = [{ hidden: true }];
  ws['!rows'] = wsrows; // ws - worksheet
  XLSX.utils.book_append_sheet(wb, ws, sheetName);
  /* generate file and send to client */
  XLSX.writeFile(wb, `${name}-${parseTime(new Date().getTime())}.xlsx`);
}

export const ExcelTitle = {
  ExcelOrderTitle: ['用户名',"上级用户",'订单号', '保单/批单号', '原订单号', '保险公司','产品名称', '投保人', '被保险人',  '总保费', '支付金额', '人数', '订单状态', '下单时间','生效日','终止日','外部订单号'],



  ExcelCashFlowTitle: ['用户名', '流水号', '财务类型', '交易类型', '交易金额', '外部交易号', '交易时间', '添加时间',],

  ExcelFundamentalsTitle: ['险种','保险公司', '条款名称', '条款类型', '条款版本', '保障利益分类', '保障利益名称','责任代码','保障利益描述', '内部编号', '备案号', '排序', '状态', '修改时间'],
  
  ExcelProductTitle: ['保险公司', '归属险种', '产品分类', '保险公司产品代码', '保险期限', '产品代码', '产品全称', '产品简称', '产品版本号', '适合人群', '状态', '修改时间'],


  ExcelSettlementTitle:["用户名",'上级用户名', '保单号','保险公司','产品名称','产品计划','投保企业','出险企业','出险人姓名','出险人证件号','工种名称','职业等级', '起保日期','保险止期','出险时间','提交时间','理赔估损','理赔实付','理赔状态'],

  ExcelSlipTitle:['用户名',"上级用户",'保单号',  '原订单号', '保险公司','产品代码','产品名称', '计划代码','计划名称','投保人', '被保险人', '雇员姓名','雇员证件号码', '雇员职业名称','雇员职业等级','保费', '保单状态', '添加时间','出单时间','生效日','终止日','外部订单号'],
  ExcelUserTitle:['用户名',"上级帐号",'手机号',  '用户类型', '用户级别','可用余额', '公司名称', '状态', '添加时间'],
  
}
