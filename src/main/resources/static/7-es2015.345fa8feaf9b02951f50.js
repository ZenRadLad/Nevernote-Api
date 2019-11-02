(window.webpackJsonp=window.webpackJsonp||[]).push([[7],{tFaf:function(o,e,n){"use strict";n.r(e);var l=n("8Y7J"),t=n("9xeW"),s=n("mI/j"),i=n("6pmS"),a=n("drq7");class b{constructor(o,e,n,l){this.router=o,this.notebookService=e,this.dialog=n,this.translateService=l,this.notebooks=[],this.state="active"}ngOnInit(){this.getNotebooks(),this.setNewNotebookDialogPlaceholder(),this.setUpdateNotebookDialogMessage()}setNewNotebookDialogPlaceholder(){this.translateService.get("APP.NOTEBOOKS.NEW_NOTEBOOK").subscribe(o=>{this.newNotebookTitle=o})}setUpdateNotebookDialogMessage(){this.translateService.get("APP.NOTEBOOKS.UPDATE_NOTEBOOK").subscribe(o=>{this.updateNotebookMessage=o})}setDeleteNotebookDialogMessage(o){this.translateService.get("APP.NOTEBOOKS.CONFIRM_DELETE_NOTEBOOK",{notebookName:o}).subscribe(o=>{this.deleteNotebookMessage=o})}openNewNotebookDialog(){this.dialog.open(i.a,{width:"250px",data:{title:this.newNotebookTitle,itemName:this.notebookName}}).afterClosed().subscribe(o=>{o&&(this.notebookName=o,this.createNewNotebook())})}createNewNotebook(){let o=new t.a;o.name=this.notebookName,o.creationDate=new Date,this.notebookService.createNotebook(o).subscribe(e=>{this.notebooks.push(o)},o=>{console.error("An error occured while creating notebook ",o)})}getNotebooks(){this.notebookService.getNotebooks().subscribe(o=>{this.notebooks=o},o=>{console.error("An error occured while getting list of notebooks ",o)})}deleteNotebook(o){this.setDeleteNotebookDialogMessage(o.name),this.dialog.open(a.a,{width:"400px",height:"170px",data:this.deleteNotebookMessage}).afterClosed().subscribe(e=>{e&&this.notebookService.deleteNotebook(o.id).subscribe(e=>{let n=this.notebooks.indexOf(o);this.notebooks.splice(n,1)},o=>{console.error("An error has occurred while deleting this notebook ",o)})})}openUpdateNotebookDialog(o){this.dialog.open(i.a,{width:"250px",data:{title:this.updateNotebookMessage,itemName:o.name}}).afterClosed().subscribe(e=>{e&&(o.name=e,this.updateNotebook(o))})}updateNotebook(o){this.notebookService.updateNotebook(o).subscribe(o=>{},o=>{console.error("An error occurred while updating the notebook ",o)})}}class u{}var c=n("t68o"),r=n("xYTU"),d=n("6n0y"),k=n("dkxh"),p=n("pMnS");class h{constructor(){this.notebookUpdateEvent=new l.m,this.notebookDeleteEvent=new l.m}updateNotebook(){this.notebookUpdateEvent.emit(this.notebook)}deleteNotebook(){this.notebookDeleteEvent.emit(this.notebook)}}var g=l.qb({encapsulation:0,styles:[["#notebookItem[_ngcontent-%COMP%]{background-color:#2a3031;margin-top:30px;width:180px;height:70px}.card-body[_ngcontent-%COMP%]{padding:1rem}.card-body[_ngcontent-%COMP%] > div[_ngcontent-%COMP%]{float:left}.title[_ngcontent-%COMP%]{width:65%;text-align:center}a[_ngcontent-%COMP%]:first-child{margin-left:10px}#notebookItem[_ngcontent-%COMP%]   a[_ngcontent-%COMP%]{visibility:hidden;cursor:pointer}#notebookItem[_ngcontent-%COMP%]:hover   a[_ngcontent-%COMP%]{visibility:visible}"]],data:{}});function C(o){return l.Nb(0,[(o()(),l.sb(0,0,null,null,9,"div",[["class","card box-shadow hvr-grow-shadow"],["id","notebookItem"]],null,null,null,null,null)),(o()(),l.sb(1,0,null,null,8,"div",[["class","card-body"]],null,null,null,null,null)),(o()(),l.sb(2,0,null,null,2,"div",[["class","title"]],null,null,null,null,null)),(o()(),l.sb(3,0,null,null,1,"p",[],null,null,null,null,null)),(o()(),l.Lb(4,null,["",""])),(o()(),l.sb(5,0,null,null,4,"div",[],null,null,null,null,null)),(o()(),l.sb(6,0,null,null,1,"a",[["class","pull-right text-danger"]],null,[[null,"click"]],(function(o,e,n){var l=!0;return"click"===e&&(l=!1!==o.component.deleteNotebook()&&l),l}),null,null)),(o()(),l.sb(7,0,null,null,0,"i",[["class","fa fa-trash-o"]],null,null,null,null,null)),(o()(),l.sb(8,0,null,null,1,"a",[["class","pull-right text-warning"]],null,[[null,"click"]],(function(o,e,n){var l=!0;return"click"===e&&(l=!1!==o.component.updateNotebook()&&l),l}),null,null)),(o()(),l.sb(9,0,null,null,0,"i",[["class","fa fa-pencil-square-o"]],null,null,null,null,null))],null,(function(o,e){o(e,4,0,e.component.notebook.name)}))}var m=n("bujt"),N=n("Fwaw"),v=n("5GAg"),f=n("omvX"),O=n("Mr+X"),w=n("Gi4r"),y=n("SVse"),E=n("iInd"),D=n("s6ns"),M=n("TSSN"),x=l.qb({encapsulation:0,styles:[[""]],data:{animation:[{type:7,name:"notebooksAnim",definitions:[{type:0,name:"active",styles:{type:6,styles:{opacity:"1"},offset:null},options:void 0},{type:1,expr:"void => *",animation:[{type:6,styles:{transform:"translateX(-30px)",opacity:"0"},offset:null},{type:4,styles:null,timings:"750ms ease-in-out"}],options:null},{type:1,expr:"* => void",animation:[{type:4,styles:{type:6,styles:{transform:"translateX(-30px)",opacity:"0"},offset:null},timings:"400ms ease-in-out"}],options:null}],options:{}}]}});function P(o){return l.Nb(0,[(o()(),l.sb(0,0,null,null,2,null,null,null,null,null,null,null)),(o()(),l.sb(1,0,null,null,1,"app-notebook",[["class","col-lg-3 col-md-3 col-sm-6 col-xs-12 cards"]],[[24,"@notebooksAnim",0]],[[null,"notebookUpdateEvent"],[null,"notebookDeleteEvent"]],(function(o,e,n){var l=!0,t=o.component;return"notebookUpdateEvent"===e&&(l=!1!==t.openUpdateNotebookDialog(n)&&l),"notebookDeleteEvent"===e&&(l=!1!==t.deleteNotebook(n)&&l),l}),C,g)),l.rb(2,49152,null,0,h,[],{notebook:[0,"notebook"]},{notebookUpdateEvent:"notebookUpdateEvent",notebookDeleteEvent:"notebookDeleteEvent"})],(function(o,e){o(e,2,0,e.context.$implicit)}),(function(o,e){o(e,1,0,e.component.state)}))}function S(o){return l.Nb(0,[(o()(),l.sb(0,0,null,null,9,"div",[["class","container"]],null,null,null,null,null)),(o()(),l.sb(1,0,null,null,5,"div",[["class","pull-right"]],null,null,null,null,null)),(o()(),l.sb(2,0,null,null,4,"button",[["mat-icon-button",""]],[[1,"disabled",0],[2,"_mat-animation-noopable",null]],[[null,"click"]],(function(o,e,n){var l=!0;return"click"===e&&(l=!1!==o.component.openNewNotebookDialog()&&l),l}),m.b,m.a)),l.rb(3,180224,null,0,N.b,[l.k,v.c,[2,f.a]],null,null),(o()(),l.sb(4,0,null,0,2,"mat-icon",[["class","mat-icon notranslate"],["role","img"]],[[2,"mat-icon-inline",null],[2,"mat-icon-no-color",null]],null,null,O.b,O.a)),l.rb(5,9158656,null,0,w.b,[l.k,w.d,[8,null],[2,w.a],[2,l.l]],null,null),(o()(),l.Lb(-1,0,["add"])),(o()(),l.sb(7,0,null,null,2,"div",[["class","row"]],null,null,null,null,null)),(o()(),l.hb(16777216,null,null,1,null,P)),l.rb(9,278528,null,0,y.k,[l.O,l.L,l.r],{ngForOf:[0,"ngForOf"]},null)],(function(o,e){var n=e.component;o(e,5,0),o(e,9,0,n.notebooks)}),(function(o,e){o(e,2,0,l.Eb(e,3).disabled||null,"NoopAnimations"===l.Eb(e,3)._animationMode),o(e,4,0,l.Eb(e,5).inline,"primary"!==l.Eb(e,5).color&&"accent"!==l.Eb(e,5).color&&"warn"!==l.Eb(e,5).color)}))}function _(o){return l.Nb(0,[(o()(),l.sb(0,0,null,null,1,"app-notebooks-list",[],null,null,null,S,x)),l.rb(1,114688,null,0,b,[E.k,s.a,D.e,M.j],null,null)],(function(o,e){o(e,1,0)}),null)}var A=l.ob("app-notebooks-list",b,_,{},{},[]),T=n("POq0"),U=n("Xd0L"),j=n("QQfA"),I=n("IP0z"),q=n("JjoW"),B=n("/Co4"),F=n("s7LF"),K=n("IheW"),L=n("cUpR"),X=n("/HVE"),J=n("oapL"),W=n("HsOI"),z=n("ZwOa"),G=n("hOhj"),H=n("BV1i"),Q=n("02hT"),V=n("Q+lL"),R=n("zMNK"),Y=n("dFDH"),Z=n("kNGD"),$=n("vvyD"),oo=n("PCNd"),eo=n("dvZr");n.d(e,"NotebooksModuleNgFactory",(function(){return no}));var no=l.pb(u,[],(function(o){return l.Bb([l.Cb(512,l.j,l.ab,[[8,[c.a,r.a,r.b,d.a,k.a,p.a,A]],[3,l.j],l.w]),l.Cb(4608,y.n,y.m,[l.t,[2,y.z]]),l.Cb(4608,T.c,T.c,[]),l.Cb(4608,U.b,U.b,[]),l.Cb(4608,j.c,j.c,[j.i,j.e,l.j,j.h,j.f,l.q,l.y,y.d,I.b,[2,y.h]]),l.Cb(5120,j.j,j.k,[j.c]),l.Cb(5120,D.c,D.d,[j.c]),l.Cb(135680,D.e,D.e,[j.c,l.q,[2,y.h],[2,D.b],D.c,[3,D.e],j.e]),l.Cb(5120,q.a,q.b,[j.c]),l.Cb(5120,B.b,B.c,[j.c]),l.Cb(4608,F.s,F.s,[]),l.Cb(4608,F.d,F.d,[]),l.Cb(4608,s.a,s.a,[K.c]),l.Cb(1073742336,y.c,y.c,[]),l.Cb(1073742336,I.a,I.a,[]),l.Cb(1073742336,U.j,U.j,[[2,U.c],[2,L.f]]),l.Cb(1073742336,X.b,X.b,[]),l.Cb(1073742336,U.t,U.t,[]),l.Cb(1073742336,N.c,N.c,[]),l.Cb(1073742336,J.c,J.c,[]),l.Cb(1073742336,T.d,T.d,[]),l.Cb(1073742336,W.d,W.d,[]),l.Cb(1073742336,z.b,z.b,[]),l.Cb(1073742336,G.c,G.c,[]),l.Cb(1073742336,H.h,H.h,[]),l.Cb(1073742336,w.c,w.c,[]),l.Cb(1073742336,U.k,U.k,[]),l.Cb(1073742336,U.r,U.r,[]),l.Cb(1073742336,Q.a,Q.a,[]),l.Cb(1073742336,V.c,V.c,[]),l.Cb(1073742336,R.f,R.f,[]),l.Cb(1073742336,j.g,j.g,[]),l.Cb(1073742336,D.k,D.k,[]),l.Cb(1073742336,U.o,U.o,[]),l.Cb(1073742336,q.d,q.d,[]),l.Cb(1073742336,Y.e,Y.e,[]),l.Cb(1073742336,Z.f,Z.f,[]),l.Cb(1073742336,B.e,B.e,[]),l.Cb(1073742336,$.a,$.a,[]),l.Cb(1073742336,M.g,M.g,[]),l.Cb(1073742336,F.r,F.r,[]),l.Cb(1073742336,F.h,F.h,[]),l.Cb(1073742336,F.q,F.q,[]),l.Cb(1073742336,oo.a,oo.a,[]),l.Cb(1073742336,E.n,E.n,[[2,E.s],[2,E.k]]),l.Cb(1073742336,u,u,[]),l.Cb(256,Z.a,{separatorKeyCodes:[eo.g]},[]),l.Cb(1024,E.i,(function(){return[[{path:"",component:b}]]}),[])])}))}}]);