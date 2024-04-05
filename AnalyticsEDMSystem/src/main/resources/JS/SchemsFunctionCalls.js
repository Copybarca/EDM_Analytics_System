

var MYAPP = (function(){
    var funPrivate = function(){
       // console.log(this)
      return 'private';
    };

    return {
        func: function(a,b){
            //console.log(this);
            var that = this;
            var helperFunc = function(a,b){
               // console.log(this)
               // console.log(that)
                that.multiply = a*b;
            }
            helperFunc(1,3)
            return a+b;
        },
        funcPublic: funPrivate
    }
})();
/*console.log(MYAPP.func(1,3));
console.log(MYAPP.funcPublic(1,3));
console.log(MYAPP.multiply);*/

var arr =[3,5]
var add = function(a,b){
    console.log(this.func(10,3));
    return a+b;
};

var sum = add.apply(MYAPP, arr);
console.log(sum)