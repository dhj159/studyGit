webpackJsonp([3],{RzR6:function(t,s){},U7mb:function(t,s,n){"use strict";Object.defineProperty(s,"__esModule",{value:!0});var e={name:"",props:["blurList"],data:function(){return{list:"",aList:[]}},watch:{blurList:function(t,s){this.aList=t},aList:function(t,s){this.list=t}},mounted:function(){this.getList()},methods:{getList:function(){var t=this;console.log(this.baseUrl),this.axios.get(this.baseUrl+"client/queryNoId").then(function(s){t.list=s.data}).catch(function(t){})},add:function(){var t=this;this.axios.get(this.baseUrl+"client/queryNoId",{params:{cname:this.cname}}).then(function(s){t.list=s.data})},turnUser:function(t){this.$router.push({name:"localDetails",query:{id:t}})}}},i={render:function(){var t=this,s=t.$createElement,n=t._self._c||s;return n("div",[n("el-container",[n("el-header",[n("el-button",{attrs:{type:"success",plain:""},on:{click:function(s){return t.$router.push({name:"addUser"})}}},[t._v("新增")])],1),t._v(" "),n("el-main",t._l(t.list,function(s){return n("div",{on:{click:function(n){return t.turnUser(s.id)}}},[n("el-row",{attrs:{gutter:20}},[n("el-col",{attrs:{span:2}},[n("div",{staticClass:"grid-content bg-purple"})]),t._v(" "),n("el-col",{attrs:{span:18}},[n("div",{staticClass:"grid-content bg-purple"},[t._v("\n              "+t._s(s.cname)+"\n            ")])]),t._v(" "),n("el-col",{attrs:{span:4}},[n("div",{staticClass:"grid-content bg-purple"})])],1),t._v(" "),n("el-row",{attrs:{gutter:20}},[n("el-col",{attrs:{span:2}},[n("div",{staticClass:"grid-content bg-purple"})]),t._v(" "),n("el-col",{attrs:{span:18}},[n("div",{staticClass:"grid-content bg-purple"},[t._v("\n              "+t._s(s.address)+"\n            ")])]),t._v(" "),n("el-col",{attrs:{span:4}},[n("div",{staticClass:"grid-content bg-purple"})])],1),t._v(" "),n("el-row",{staticStyle:{"margin-bottom":"-20px"},attrs:{gutter:20}},[n("el-col",{attrs:{span:2}},[n("div",{staticClass:"grid-content bg-purple"})]),t._v(" "),n("el-col",{attrs:{span:18}},[n("div",{staticClass:"grid-content bg-purple"},[n("i",{staticClass:"el-icon-phone-outline"}),t._v("\n              "+t._s(s.linkmanList[0].ltel)+"\n            ")])]),t._v(" "),n("el-col",{attrs:{span:4}},[n("div",{staticClass:"grid-content bg-purple"})])],1),t._v(" "),n("el-divider")],1)}),0)],1)],1)},staticRenderFns:[]};var a=n("VU/8")(e,i,!1,function(t){n("RzR6")},"data-v-8f70cf1e",null);s.default=a.exports}});
//# sourceMappingURL=3.6e34cced48c295449016.js.map