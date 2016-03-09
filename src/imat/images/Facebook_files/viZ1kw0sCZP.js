/*!CK:1342937447!*//*1456831966,*/

if (self.CavalryLogger) { CavalryLogger.start_js(["kLLBo"]); }

__d('FBChecklistItem.react',['cx','invariant','React','joinClasses'],function a(b,c,d,e,f,g,h,i){if(c.__markCompiled)c.__markCompiled();var j=c('React').PropTypes,k=c('React').createClass({displayName:'FBChecklistItem',propTypes:{context:j.oneOf(['block','inline']),paddingLeft:j.oneOf(['none','small','normal']),paddingRight:j.oneOf(['none','small','normal'])},getDefaultProps:function(){return {context:'block',paddingLeft:'normal',paddingRight:'normal'};},render:function(){!(Array.isArray(this.props.children)&&(this.props.children.length===2||this.props.children.length===3))?i(0):undefined;var l,m,n;if(this.props.children.length===3){l=this.props.children[0];m=this.props.children[1];n=this.props.children[2];}else{l=null;m=this.props.children[0];n=this.props.children[1];}var o=this.props.context==='block'?'div':'span',p=null;if(l)p=c('React').createElement(o,{className:"_pur _pus"},l);var q=this.props.paddingLeft,r=this.props.paddingRight,s="_put"+(q==='none'?' '+"_puu":'')+(q==='small'?' '+"_puv":'')+(r==='none'?' '+"_puw":'')+(r==='small'?' '+"_pux":'');return (c('React').createElement(o,babelHelpers['extends']({},this.props,{className:c('joinClasses')(this.props.className,s)}),p,c('React').createElement(o,{className:"_puy"+(!l?' '+"_pus":'')},m),c('React').createElement(o,{className:"_puz"},n)));}});f.exports=k;},null);
__d('FBChecklistPagerMixin',['React'],function a(b,c,d,e,f,g){if(c.__markCompiled)c.__markCompiled();var h=c('React').PropTypes,i={propTypes:{endpoint:h.string.isRequired,onExhaustion:h.func.isRequired,onFetch:h.func,placeholder:h.string.isRequired},getInitialState:function(){return {endpoint:this.props.endpoint,loading:false};},_shouldFetchMore:function(){if(this.state.loading||!this.state.endpoint)return false;this.setState({loading:true});return true;},_onFetch:function(j){this.setState({endpoint:j.endpoint,loading:false});this.props.onFetch&&this.props.onFetch(j);!j.endpoint&&this.props.onExhaustion();}};f.exports=i;},null);
__d('FBChecklistPager.react',['cx','AsyncRequest','Keys','FBChecklistPagerMixin','React','XUIText.react'],function a(b,c,d,e,f,g,h){if(c.__markCompiled)c.__markCompiled();var i=c('React').createClass({displayName:'FBChecklistPager',mixins:[c('FBChecklistPagerMixin')],fetchMore:function(){if(!this._shouldFetchMore())return;new (c('AsyncRequest'))().setURI(this.state.endpoint).setHandler(this._fetchHandler).send();},render:function(){var j="_4j3_"+(this.state.loading?' '+"_4j40":'');return (c('React').createElement('div',{className:j,onClick:this._clickHandler,onKeyPress:this._keyPressHandler,tabIndex:0},c('React').createElement(c('XUIText.react'),{size:'small',weight:'bold'},this.props.placeholder),c('React').createElement('span',{className:"_4j41"})));},_clickHandler:function(event){event.preventDefault();this.fetchMore();},_fetchHandler:function(j){var k=j.getPayload();this._onFetch(k);},_keyPressHandler:function(event){switch(event.charCode){case c('Keys').SPACE:case c('Keys').RETURN:this.fetchMore();}}});f.exports=i;},null);
__d('requiredIfPropIsTruthy',['sprintf'],function a(b,c,d,e,f,g){if(c.__markCompiled)c.__markCompiled();function h(i,j){return function(k,l,m,n){if(!k[i])return;if(k[l]===undefined)return new Error(c('sprintf')('Must supply %s to %s if property %s is truthy.',l,m,i));return j(k,l,m,n);};}f.exports=h;},null);
__d('FBChecklistProps',['React','requiredIfPropIsTruthy'],function a(b,c,d,e,f,g){if(c.__markCompiled)c.__markCompiled();var h=c('React').PropTypes,i=h.shape({checked:h.bool,id:h.oneOfType([h.string,h.number]).isRequired,image:h.oneOfType([h.string,h.object]),name:h.string,subtitle:h.string,title:h.oneOfType([h.string,h.object]).isRequired}),j={baseName:h.string.isRequired,fetchOnScroll:h.bool,imageSize:h.number,maxHeight:h.number,multiSelect:h.bool,onBlur:h.func,onChange:h.func,onExhaust:h.func,onFetch:h.func,onFocus:h.func,options:h.arrayOf(i).isRequired,overflow:h.oneOf(['scroll','show']),pagerEndpoint:c('requiredIfPropIsTruthy')('showPager',h.string),pagerPlaceholder:c('requiredIfPropIsTruthy')('showPager',h.string),requireSelect:h.bool,scrollOnFetch:h.bool,showPager:h.bool};f.exports=j;},null);
__d('FBChecklistScrollMixin',['Animation','ReactDOM','Scroll','Style','highlight','throttle'],function a(b,c,d,e,f,g){if(c.__markCompiled)c.__markCompiled();var h=400,i=100,j=300,k=250,l={componentWillUnmount:function(){this.throttled=null;},scrollToBottom:function(){this._scrollTo(this._getScrollArea().scrollHeight);},scrollOptionToTop:function(n){if(!this.state.options.has(n))return;var o=c('ReactDOM').findDOMNode(this.refs[n]);this._scrollTo(o.offsetTop-1,null,j);},scrollToOption:function(n){if(!this.state.options.has(n))return;var o=this._getScrollArea(),p=c('ReactDOM').findDOMNode(this.refs[n]),q=p.offsetTop+p.offsetHeight,r=c('Scroll').getTop(o)+o.offsetHeight,s=function(){var t=this._getBackgroundColor();t&&c('highlight')(p,null,t);}.bind(this);if(p.offsetTop<c('Scroll').getTop(o)){this._scrollTo(p.offsetTop-1,s);}else if(q>r){this._scrollTo(q-o.offsetHeight,s);}else{s();return;}},_getScrollArea:function(){return this.refs.container.getArea();},_getBackgroundColor:function(){var n=this._getScrollArea();while(n){var o=c('Style').get(n,'backgroundColor');if(!m(o))return o;n=n.parentElement;}return null;},_maybeFetchMoreOptions:function(){if(this.props.fetchOnScroll&&this.state.showPager&&this.refs.container.getScrollPosition().bottom<h)this.refs.pager.fetchMore();},_scrollHandler:function(){if(!this.throttled)this.throttled=c('throttle')(this._maybeFetchMoreOptions,k,this);this.throttled();},_scrollTo:function(n,o,p){new (c('Animation'))(this._getScrollArea()).to('scrollTop',n).ease(c('Animation').ease.end).duration(p||i).ondone(function(){o&&o();}).go();}};function m(n){if(!n.startsWith('rgba'))return false;var o=n.split(',');return o.length===4&&parseFloat(o[3])===0;}f.exports=l;},null);
__d('ShadowedScrollableArea.react',['cx','React','ReactDOM','Scroll','Vector','throttle','joinClasses'],function a(b,c,d,e,f,g,h){if(c.__markCompiled)c.__markCompiled();var i=c('React').PropTypes,j=500,k=c('React').createClass({displayName:'ShadowedScrollableArea',propTypes:{maxHeight:i.number.isRequired},getInitialState:function(){return {showBottom:false,showTop:false};},render:function(){var l=c('React').createElement('div',babelHelpers['extends']({},this.props,{className:c('joinClasses')(this.props.className,"scrollable"),ref:'area',style:babelHelpers['extends']({},this.props.style||{},{maxHeight:this.props.maxHeight})}),this.props.children),m="_2yy7"+(this.state.showBottom?' '+"_2yy8":'')+(this.state.showTop?' '+"_2yy9":'');return (c('React').createElement('div',{className:m,onScroll:this._scrollHandler},l));},componentWillUnmount:function(){this.throttled=null;},getArea:function(){return c('ReactDOM').findDOMNode(this.refs.area);},getScrollPosition:function(){var l=c('ReactDOM').findDOMNode(this.refs.area),m=this._getAreaDimensions().height;return {bottom:l.scrollHeight-c('Scroll').getTop(l)-m,top:c('Scroll').getTop(l)};},updateShadows:function(){if(this._hasOverflow()){var l=this.getScrollPosition();this.setState({showBottom:l.bottom!==0,showTop:l.top!==0});}else if(this.state.showBottom||this.state.showTop)this.setState({showBottom:false,showTop:false});},_getAreaDimensions:function(){var l=c('ReactDOM').findDOMNode(this.refs.area);return {height:c('Vector').getElementDimensions(l).y,scrollHeight:l.scrollHeight};},_hasOverflow:function(){var l=this._getAreaDimensions();return l.scrollHeight>l.height;},_scrollHandler:function(event){if(!this.throttled)this.throttled=c('throttle')(this.updateShadows,j,this);this.throttled();}});f.exports=k;},null);
__d('FBChecklist.react',['cx','FBChecklistProps','FBChecklistScrollMixin','React','Image.react','FBChecklistItem.react','FBChecklistPager.react','ShadowedScrollableArea.react','XUIGrayText.react','uniqueID'],function a(b,c,d,e,f,g,h){if(c.__markCompiled)c.__markCompiled();var i=c('React').PropTypes,j=248,k=32,l=c('React').createClass({displayName:'FBChecklist',mixins:[c('FBChecklistScrollMixin')],propTypes:babelHelpers['extends']({titleFontShade:i.oneOf(['dark','light','medium'])},c('FBChecklistProps')),getDefaultProps:function(){return {maxHeight:j,overflow:'show',imageSize:k,titleFontShade:'medium'};},getInitialState:function(){return {instanceID:c('uniqueID')()};},render:function(){var m=this.props.options.map(function(p){var q=null;if(p.image)q=c('React').createElement('span',null,c('React').createElement(c('Image.react'),{alt:p.title,height:this.props.imageSize,src:p.image,width:this.props.imageSize}));var r=null;if(p.subtitle)r=c('React').createElement(c('XUIGrayText.react'),{className:"_25_9",weight:'normal'},p.subtitle);var s="_25_a"+(p.checked?' '+"_25_b":'')+(p.focused?' '+"_25_c":''),t=null;if(p.iconSrc&&p.iconExplanation)t=c('React').createElement(c('Image.react'),{'aria-label':p.iconExplanation,className:"_25_e",'data-hover':'tooltip','data-tooltip-content':p.iconExplanation,'data-tooltip-position':'above',src:p.iconSrc});var u=[this.state.instanceID,p.id].join('_');return (c('React').createElement('label',{className:s,htmlFor:u,key:p.id,ref:p.id},c('React').createElement(c('FBChecklistItem.react'),{context:'inline',paddingLeft:this.props.paddingLeft,paddingRight:this.props.paddingRight},q,c('React').createElement('span',null,c('React').createElement(c('XUIGrayText.react'),{className:"_25_f",shade:this.props.titleFontShade,size:'small',weight:'bold'},p.title,t),r),c('React').createElement('span',{className:"_25_g"},c('React').createElement('span',{className:"_25_h"}),c('React').createElement('input',{checked:p.checked,className:"_25_i",id:u,name:this._getOptionNameOrFallback(p),onBlur:this.props.onBlur,onChange:this.props.onChange,onFocus:this.props.onFocus,type:'checkbox',value:p.id})))));}.bind(this)),n=null;if(this.props.showPager)n=c('React').createElement(c('FBChecklistPager.react'),{endpoint:this.props.pagerEndpoint,onExhaustion:this.props.onExhaust,onFetch:this.props.onFetch,placeholder:this.props.pagerPlaceholder,ref:'pager'});var o=c('React').createElement('fieldset',{className:"_25_j"},m,n);if(this.props.overflow==='scroll')return (c('React').createElement(c('ShadowedScrollableArea.react'),{maxHeight:this.props.maxHeight,onScroll:this._scrollHandler,ref:'container'},o));return o;},_getOptionNameOrFallback:function(m){var n=m.name||this.props.baseName;if(this.props.multiSelect&&!n.endsWith('[]'))n+='['+m.id+']';return n;}});f.exports=l;},null);
__d('OrderedMap',['invariant','Object.assign'],function a(b,c,d,e,f,g,h){'use strict';if(c.__markCompiled)c.__markCompiled();var i='key:';function j(q,r){var s={};for(var t=0;t<q.length;t++){var u=q[t],v=r(u);l(v);var w=i+v;!!(w in s)?h(0):undefined;s[w]=u;}return s;}function k(q,r){this._normalizedObj=q;this._computedPositions=null;this.length=r;}function l(q){!(q!==''&&(typeof q==='string'||typeof q==='number'))?h(0):undefined;}function m(q,r,s){!(typeof q==='number'&&typeof r==='number'&&r>=0&&q>=0&&q+r<=s)?h(0):undefined;}function n(q,r){!(q&&q.constructor===Object&&(!r||r.constructor===Object))?h(0):undefined;var s={},t=0,u;for(u in q)if(q.hasOwnProperty(u)){s[u]=q[u];t++;}for(u in r)if(r.hasOwnProperty(u)){if(!(u in s))t++;s[u]=r[u];}return new k(s,t);}var o={has:function(q){l(q);var r=i+q;return r in this._normalizedObj;},get:function(q){l(q);var r=i+q;return this.has(q)?this._normalizedObj[r]:undefined;},merge:function(q){!(q instanceof k)?h(0):undefined;return n(this._normalizedObj,q._normalizedObj);},map:function(q,r){return this.mapRange(q,0,this.length,r);},mapRange:function(q,r,s,t){var u=this._normalizedObj,v={},w=0;m(r,s,this.length);var x=r+s-1;for(var y in u)if(u.hasOwnProperty(y)){if(w>=r){if(w>x)break;var z=u[y];v[y]=q.call(t,z,y.substr(i.length),w);}w++;}return new k(v,s);},filter:function(q,r){return this.filterRange(q,0,this.length,r);},filterRange:function(q,r,s,t){var u={},v=0;this.forEachRange(function(w,x,y){if(q.call(t,w,x,y)){var z=i+x;u[z]=w;v++;}},r,s);return new k(u,v);},forEach:function(q,r){this.forEachRange(q,0,this.length,r);},forEachRange:function(q,r,s,t){m(r,s,this.length);var u=this._normalizedObj,v=0,w=r+s-1;for(var x in u)if(u.hasOwnProperty(x)){if(v>=r){if(v>w)break;var y=u[x];q.call(t,y,x.substr(i.length),v);}v++;}},mapKeyRange:function(q,r,s,t){var u=this.indexOfKey(r),v=this.indexOfKey(s);!(u!==undefined&&v!==undefined)?h(0):undefined;!(v>=u)?h(0):undefined;return this.mapRange(q,u,v-u+1,t);},forEachKeyRange:function(q,r,s,t){var u=this.indexOfKey(r),v=this.indexOfKey(s);!(u!==undefined&&v!==undefined)?h(0):undefined;!(v>=u)?h(0):undefined;this.forEachRange(q,u,v-u+1,t);},keyAtIndex:function(q){var r=this._getOrComputePositions(),s=r.keyByIndex[q];return s?s.substr(i.length):undefined;},keyAfter:function(q){return this.nthKeyAfter(q,1);},keyBefore:function(q){return this.nthKeyBefore(q,1);},nthKeyAfter:function(q,r){var s=this.indexOfKey(q);!(s!==undefined)?h(0):undefined;return this.keyAtIndex(s+r);},nthKeyBefore:function(q,r){return this.nthKeyAfter(q,-r);},indexOfKey:function(q){l(q);var r=i+q,s=this._getOrComputePositions(),t=s.indexByKey[r];return t===undefined?undefined:t;},toArray:function(){var q=[],r=this._normalizedObj;for(var s in r)if(r.hasOwnProperty(s))q.push(r[s]);return q;},_getOrComputePositions:function(){var q=this._computedPositions;if(!q)this._computePositions();return this._computedPositions;},_computePositions:function(){this._computedPositions={keyByIndex:{},indexByKey:{}};var q=this._computedPositions.keyByIndex,r=this._computedPositions.indexByKey,s=0,t=this._normalizedObj;for(var u in t)if(t.hasOwnProperty(u)){q[s]=u;r[u]=s;s++;}}};c('Object.assign')(k.prototype,o);var p={from:function(q){!(q instanceof k)?h(0):undefined;return n(q._normalizedObj,null);},fromArray:function(q,r){!Array.isArray(q)?h(0):undefined;!(typeof r==='function')?h(0):undefined;return new k(j(q,r),q.length);}};f.exports=p;},null);
__d('FBChecklistStateMixin',['OrderedMap','requestAnimationFrame'],function a(b,c,d,e,f,g){if(c.__markCompiled)c.__markCompiled();var h={addOption:function(i,j,k,l,m,n){this.props.multiSelect||this._uncheckAllOptions();this._insertSingleOption.apply(this,[false].concat(Array.prototype.slice.call(arguments)));},replaceWithSingleOption:function(i,j,k,l,m,n){this._insertSingleOption.apply(this,[true].concat(Array.prototype.slice.call(arguments)));this._optionsWereReplaced&&this._optionsWereReplaced();},clearSelection:function(){this._uncheckAllOptions();},getSelection:function(){var i={};this.state.options.forEach(function(j,k){if(j.checked)i[k]=j;});return i;},getSelectionCount:function(){return Object.keys(this.getSelection()).length;},hasSelection:function(){return this.getSelectionCount()>0;},getInitialState:function(){return {options:c('OrderedMap').fromArray(this.props.options,function(i){return i.id;}),showPager:this.props.showPager};},shouldComponentUpdate:function(i,j){return (this.state.options!==j.options||this.state.showPager!==j.showPager);},_changeHandler:function(event){var i=this._getEventTarget(event);if(this.props.requireSelect&&!i.checked&&this.getSelectionCount()===1)return;if(i.checked&&!this.props.multiSelect)this._uncheckAllOptions();this.setState({options:this.state.options.map(function(j,k){if(k===i.value)j.checked=i.checked;return j;})},function(){if(this.props.onChange)this.props.onChange(this.getSelection());}.bind(this));},_exhaustionHandler:function(){this.setState({showPager:false});},_fetchHandler:function(i){if(!i.options.length){this._exhaustionHandler();return;}this._insertOptions(c('OrderedMap').fromArray(i.options,function(j){return j.id;}),false,function(){if(this.props.scrollOnFetch&&this.scrollOptionToTop)this.scrollOptionToTop(i.options[0].id);}.bind(this));if(this.refs.container&&this.refs.container.updateShadows)c('requestAnimationFrame')(this.refs.container.updateShadows);},_insertSingleOption:function(i,j,k,l,m,n,o){var p=c('OrderedMap').fromArray([{id:j,title:k,subtitle:l,image:m,checked:!!n,name:o}],function(q){return q.id;});this._insertOptions(p,i);if(this._optionWasAdded)c('requestAnimationFrame')(function(){return this._optionWasAdded(j);}.bind(this));},_insertOptions:function(i,j,k){this.setState({options:j?i:this.state.options.merge(i)},k);},_uncheckAllOptions:function(){this.setState({options:this.state.options.map(function(i){i.checked=false;return i;})});},_getEventTarget:function(event){return event.target;}};f.exports=h;},null);
__d('FBChecklistWrapper.react',['FBChecklistStateMixin','React','FBChecklist.react'],function a(b,c,d,e,f,g){if(c.__markCompiled)c.__markCompiled();var h=c('React').createClass({displayName:'FBChecklistWrapper',mixins:[c('FBChecklistStateMixin')],propTypes:{},render:function(){return (c('React').createElement(c('FBChecklist.react'),babelHelpers['extends']({},this.props,{onBlur:this._blurHandler,onChange:this._changeHandler,onExhaust:this._exhaustionHandler,onFetch:this._fetchHandler,onFocus:this._focusHandler,options:this.state.options.toArray(),ref:'checklist',showPager:this.state.showPager})));},scrollToBottom:function(){this.refs.checklist.scrollToBottom();},scrollOptionToTop:function(i){this.refs.checklist.scrollOptionToTop(i);},scrollToOption:function(i){this.refs.checklist.scrollToOption(i);},_blurHandler:function(event){var i=this._getEventTarget(event);this.setState({options:this.state.options.map(function(j,k){if(k===i.value)j.focused=false;return j;})});},_focusHandler:function(event){var i=this._getEventTarget(event);this.setState({options:this.state.options.map(function(j,k){j.focused=k===i.value;return j;})});}});f.exports=h;},null);
__d('SearchSourceWithMetrics',['AbstractSearchSource'],function a(b,c,d,e,f,g){var h,i;if(c.__markCompiled)c.__markCompiled();h=babelHelpers.inherits(j,c('AbstractSearchSource'));i=h&&h.prototype;function j(k,l){'use strict';i.constructor.call(this);this.searchSource=k;this.reporter=l;}j.prototype.searchImpl=function(k,l,m){'use strict';this.reporter.reportQueryBegin(k);if(m)this.reporter.reportStrategyName(m.strategyName);var n=Date.now();this.searchSource.search(k,function(o,p){this.reporter.reportQueryComplete(Date.now()-n,0);l(o,p);}.bind(this),m);};j.prototype.bootstrapImpl=function(k){'use strict';this.reporter.reportBootstrapBegin();return this.searchSource.bootstrap(function(){this.searchSource.getBootstrappedEntries(function(l){this.reporter.reportBootstrapComplete(l.map(function(m){return m.getType();}));k();}.bind(this));}.bind(this));};f.exports=j;},null);
__d("TypeaheadMetricCounter",[],function a(b,c,d,e,f,g){if(c.__markCompiled)c.__markCompiled();function h(){"use strict";this.reset();}h.prototype.reset=function(){"use strict";this.stats={};this.avgStats={};};h.prototype.recordStat=function(i,j){"use strict";this.stats[i]=j;};h.prototype.recordCountStat=function(i){"use strict";var j=this.stats[i];this.stats[i]=j?j+1:1;};h.prototype.recordAvgStat=function(i,j){"use strict";if(this.avgStats[i]){this.avgStats[i][0]+=j;this.avgStats[i][1]+=1;}else this.avgStats[i]=[j,1];};h.prototype.hasStats=function(){"use strict";return !!Object.keys(this.stats).length;};h.prototype.getStats=function(){"use strict";var i=babelHelpers["extends"]({},this.stats);for(var j in this.avgStats){var k=this.avgStats[j];i[j]=k[0]/k[1];}return i;};f.exports=h;},null);
__d('TypeaheadMetricReporter',['AsyncRequest','QueriesHistory','TypeaheadMetricCounter','mixInEventEmitter'],function a(b,c,d,e,f,g){if(c.__markCompiled)c.__markCompiled();var h='/ajax/typeahead/record_basic_metrics.php',i=1000;function j(k){'use strict';this.counter=new (c('TypeaheadMetricCounter'))();this.extraData=Object.assign({},k);this.bootstrapped=false;this.bootstrapBegin=Date.now();this.queriesHistory=new (c('QueriesHistory'))(i);this.lastQuery=null;this.sessionActive=false;this.lastNotBackspacedQuery='';this.lastRequestID=null;this.requestIDHistory=[];this.$TypeaheadMetricReporter1();}j.prototype.getSID=function(){'use strict';return this.sid;};j.prototype.sessionStart=function(){'use strict';this.sessionActive=true;this.sessionStartTime=Date.now();};j.prototype.sessionEnd=function(){'use strict';if(this.sessionActive){this.$TypeaheadMetricReporter2();this.$TypeaheadMetricReporter1();this.sessionActive=false;}};j.prototype.sessionPause=function(){'use strict';if(this.sessionActive){this.$TypeaheadMetricReporter2();this.sessionActive=false;}};j.prototype.sessionDeactivate=function(){'use strict';this.sessionActive=false;};j.prototype.reportSelect=function(k,l,m,n,o,p){'use strict';o=o==null?this.lastQuery:o;this.counter.recordStat('selected_id',k||'SELECT_NULL');this.counter.recordStat('selected_type',l);this.counter.recordStat('selected_position',m);this.counter.recordStat('selected_with_mouse',n?1:0);this.counter.recordStat('selected_query',o);for(var q in p)this.counter.recordStat(q,p[q]);};j.prototype.reportStrategyName=function(k){'use strict';this.counter.recordStat('strategy_name',k);};j.prototype.reportBootstrapBegin=function(){'use strict';this.bootstrapBegin=Date.now();};j.prototype.reportBootstrapDirty=function(){'use strict';this.bootstrapped=false;};j.prototype.reportBootstrapComplete=function(k){'use strict';this.counter.recordAvgStat('bootstrap_latency',Date.now()-this.bootstrapBegin);var l={};k.forEach(function(m){l[m]=(l[m]||0)+1;});this.counter.recordStat('bootstrap_response_types',l);this.bootstrapped=true;};j.prototype.reportQueryBegin=function(k){'use strict';this.counter.recordStat('query',k);this.counter.recordCountStat('num_queries');this.queriesHistory.add(k);if(this.lastNotBackspacedQuery.indexOf(k)!==0)this.lastNotBackspacedQuery=k;this.lastQuery=k;this.lastQueryTime=Date.now();};j.prototype.reportRequestID=function(k){'use strict';this.lastRequestID=k;this.requestIDHistory.push(k);};j.prototype.reportQueryComplete=function(k,l){'use strict';this.counter.recordAvgStat('avg_query_latency',k);if(l!==undefined)this.counter.recordStat('filtered_count',l);};j.prototype.reportResults=function(k,l){'use strict';this.results=k;if(l===this.lastQuery)this.counter.recordStat('last_query_latency',Date.now()-this.lastQueryTime);};j.prototype.reportResultsAds=function(k){'use strict';this.resultsAds=k;};j.prototype.$TypeaheadMetricReporter1=function(){'use strict';this.sid=Math.floor(Date.now()*Math.random());this.lastNotBackspacedQuery='';this.queriesHistory.reset();this.results=null;this.resultsAds=null;this.lastRequestID=null;this.requestIDHistory=[];this.counter.reset();this.emit&&this.emit('reset',{sid:this.sid});};j.prototype.$TypeaheadMetricReporter3=function(){'use strict';var k={};for(var l in this.extraData){var m=this.extraData[l];k[l]=typeof m==='function'?m():m;}return k;};j.prototype.$TypeaheadMetricReporter2=function(){'use strict';if(this.counter.hasStats()){this.counter.recordStat('session_time',Date.now()-this.sessionStartTime);if(this.results)this.counter.recordStat('candidate_results',JSON.stringify(this.results));if(this.resultsAds)this.counter.recordStat('candidate_results_ads',JSON.stringify(this.resultsAds));if(this.sid)this.counter.recordStat('sid',this.sid);if(this.lastNotBackspacedQuery)this.counter.recordStat('last_not_backspaced_query',this.lastNotBackspacedQuery);this.counter.recordStat('queries_history',JSON.stringify(this.queriesHistory.getQueries()));if(this.bootstrapped)this.counter.recordStat('bootstrapped',1);if(this.lastRequestID)this.counter.recordStat('request_id',this.lastRequestID);if(this.requestIDHistory.length)this.counter.recordStat('request_ids',this.requestIDHistory);var k=babelHelpers['extends']({},this.counter.getStats(),this.$TypeaheadMetricReporter3());new (c('AsyncRequest'))().setURI(h).setMethod('POST').setData({stats:k}).send();this.emit&&this.emit('submitted',k);}};c('mixInEventEmitter')(j,{reset:true,submitted:true});f.exports=j;},null);
__d('ProfileMaskedTypeahead.react',['cx','AbstractSearchSource','React','SearchSourceWithMetrics','TypeaheadMetricReporter','XUITypeahead.react','joinClasses','requiredIfPropIsTruthy'],function a(b,c,d,e,f,g,h){if(c.__markCompiled)c.__markCompiled();var i=c('React').PropTypes,j=c('React').createClass({displayName:'ProfileMaskedTypeahead',propTypes:{metricsData:c('requiredIfPropIsTruthy')('useMetrics',i.object),queryName:i.string,requireSelection:i.bool,searchSource:i.instanceOf(c('AbstractSearchSource')).isRequired,selectionName:i.string,useMetrics:i.bool},getDefaultProps:function(){return {clearable:true,highlightOnSelect:true,queryName:'query',requireSelection:false,selectionName:'selected_id'};},componentDidUpdate:function(){var k=this.refs.typeahead.getTextFieldDOM(),l=k.scrollWidth>k.clientWidth;if(this.state.useMask!==l)this.setState({useMask:l});},getInitialState:function(){var k=null,l=this.props.searchSource;if(this.props.useMetrics){k=new (c('TypeaheadMetricReporter'))(this.props.metricsData);l=new (c('SearchSourceWithMetrics'))(l,k);}return {metricReporter:k,searchSource:l,useMask:false};},focusInput:function(){this.refs.typeahead.focusInput();},getTextFieldDOM:function(){return this.refs.typeahead.getTextFieldDOM();},render:function(){var k=this.props.selectedEntry,l=k?k.getTitle():this.props.queryString,m=null;if(this.state.useMask)m=c('React').createElement('div',{className:"_4ejq"});var n=null,o=null;if(k)n=c('React').createElement('input',{name:this.props.selectionName,type:'hidden',value:k.getUniqueID()});if(l)o=c('React').createElement('input',{name:this.props.queryName,type:'hidden',value:l});var p=c('joinClasses')(this.props.className,"_4ejr"+(this.props.clearable?' '+"_4ejs":'')+(this.props.highlightOnSelect?' '+"_4ejt":'')+(!!k?' '+"_4eju":'')+(this.props.tallInput?' '+"_4ejv":''));return (c('React').createElement('div',{className:p},c('React').createElement(c('XUITypeahead.react'),babelHelpers['extends']({},this.props,{className:"_4ejw",onBlur:this._blurHandler,onFocus:this._focusHandler,onSelectAttempt:this._selectHandler,onTypeaheadVisibilityChanged:this._typeaheadVisibilityChangeHandler,queryString:l,ref:'typeahead',searchSource:this.state.searchSource})),m,o,n));},_blurHandler:function(){if(this.props.useMetrics)this.state.metricReporter.sessionEnd();if(this.props.requireSelection&&!this.props.selectedEntry&&this.props.onClear)this.props.onClear();if(this.props.onBlur)this.props.onBlur();},_focusHandler:function(){if(this.props.useMetrics)this.state.metricReporter.sessionStart();if(this.props.onFocus)this.props.onFocus();},_selectHandler:function(k,event){if(this.props.useMetrics&&k){this.state.metricReporter.reportSelect(k.getUniqueID(),k.getType(),k.getOrder(),!!event&&event.button>=0,this.props.queryString);this.state.metricReporter.sessionEnd();}if(this.props.onSelectAttempt)this.props.onSelectAttempt(k);},_typeaheadVisibilityChangeHandler:function(k,l){if(this.props.useMetrics){var m=l.filter(function(n){return !isNaN(parseInt(n.getUniqueID(),10));});this.state.metricReporter.reportResults(m.map(function(n){return n.getUniqueID();}));}}});f.exports=j;},null);
__d('ProfileTypeaheadWrapperMixin',['React','SearchableEntry'],function a(b,c,d,e,f,g){if(c.__markCompiled)c.__markCompiled();var h=c('React').PropTypes,i={propTypes:{initialEntry:h.instanceOf(c('SearchableEntry')),initialQuery:h.string},getDefaultProps:function(){return {initialEntry:null,initialQuery:''};},getInitialState:function(){return {queryString:this.props.initialQuery,selectedEntry:this.props.initialEntry};},clearSelection:function(){this.setState({queryString:'',selectedEntry:null},this.props.onClear);},focusInput:function(){this.refs.typeahead.focusInput();},getQueryString:function(){return this.state.queryString;},getSelectedEntry:function(){return this.state.selectedEntry;},_changeHandler:function(event){this.setState({queryString:event.target.value,selectedEntry:null},function(){if(this.props.onChange)this.props.onChange(event);}.bind(this));},_selectHandler:function(j,event){this.setState({selectedEntry:j},function(){if(this.props.onSelectAttempt)this.props.onSelectAttempt(j,event);}.bind(this));}};f.exports=i;},null);
__d('ProfileMaskedTypeaheadWrapper.react',['ProfileTypeaheadWrapperMixin','React','ProfileMaskedTypeahead.react'],function a(b,c,d,e,f,g){if(c.__markCompiled)c.__markCompiled();var h=c('React').createClass({displayName:'ProfileMaskedTypeaheadWrapper',mixins:[c('ProfileTypeaheadWrapperMixin')],propTypes:{},render:function(){return (c('React').createElement(c('ProfileMaskedTypeahead.react'),babelHelpers['extends']({},this.props,{onChange:this._changeHandler,onClear:this.clearSelection,onSelectAttempt:this._selectHandler,queryString:this.state.queryString,ref:'typeahead',selectedEntry:this.state.selectedEntry})));}});f.exports=h;},null);
__d('FBTypeaheadChecklist.react',['React','FBChecklistWrapper.react','ProfileMaskedTypeaheadWrapper.react','ProfileTypeaheadContainer.react','XUITypeahead.react'],function a(b,c,d,e,f,g){if(c.__markCompiled)c.__markCompiled();var h=c('React').PropTypes,i=c('React').createClass({displayName:'FBTypeaheadChecklist',propTypes:{excludedEntries:h.object,onChange:h.func,placeholder:h.string.isRequired,requireSelection:h.bool,searchSource:h.object.isRequired,typeaheadName:h.string,queryName:function(j,k,l){if(!j.multiSelect&&!j[k])return new Error('Must supply a query name to '+l+' for '+'single-selection mode.');}},getDefaultProps:function(){return {overflow:'scroll'};},getInitialState:function(){return {queryString:''};},render:function(){var j={onSelectAttempt:this._selectHandler,placeholder:this.props.placeholder,searchSource:this.props.searchSource},k=null;if(this.props.multiSelect){k=c('React').createElement(c('XUITypeahead.react'),babelHelpers['extends']({},j,{excludedEntries:this.props.excludedEntries,onBlur:this._typeaheadBlurHandler,onChange:this._typeaheadChangeHandler,queryString:this.state.queryString,ref:'typeahead'}));}else k=c('React').createElement(c('ProfileMaskedTypeaheadWrapper.react'),babelHelpers['extends']({},j,{queryName:this.props.queryName,ref:'typeahead',requireSelection:this.props.requireSelection,selectionName:this.props.typeaheadName}));return (c('React').createElement('div',{className:this.props.className},c('React').createElement(c('FBChecklistWrapper.react'),babelHelpers['extends']({},this.props,{onChange:this._checklistChangeHandler,ref:'checklist'})),c('React').createElement(c('ProfileTypeaheadContainer.react'),null,k)));},clearSelection:function(){return this.refs.checklist.clearSelection();},getSelection:function(){return this.refs.checklist.getSelection();},_checklistChangeHandler:function(j){this.props.multiSelect||this.refs.typeahead.clearSelection();this.props.onChange&&this.props.onChange(j);},_selectHandler:function(j){if(!j)return;if(!this.props.multiSelect){this.refs.checklist.clearSelection();return;}this.refs.checklist.addOption(j.getUniqueID(),j.getTitle(),j.getSubtitle(),j.getPhoto(),true,this.props.typeaheadName);this.setState({queryString:''});setTimeout(function(){this.refs.checklist.scrollToOption(j.getUniqueID());}.bind(this),0);},_typeaheadBlurHandler:function(){this.props.requireSelection&&this.setState({queryString:''});},_typeaheadChangeHandler:function(event){this.setState({queryString:event.target.value});}});f.exports=i;},null);