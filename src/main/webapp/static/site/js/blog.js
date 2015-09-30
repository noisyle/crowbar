

var mainApp = angular.module("mainApp", ['ngRoute', 'ngSanitize']);
mainApp.config(['$routeProvider', function($routeProvider) {
  $routeProvider
	.when('/', {templateUrl: 'home', controller: 'HomeController'})
	.when('/article/:id', {templateUrl: 'article', controller: 'ArticleController'})
	.when('/about', {templateUrl: 'about', controller: 'AboutController'})
	.when('/contact', {templateUrl: 'contact', controller: 'ContactController'})
	.otherwise({redirectTo: '/'});
}]);
mainApp.filter('unsafe', ['$sce', function($sce){
    return function(text){
    	if(!text) return text;
		var s = text.replace(/&lt;/g, '<')
			.replace(/&gt;/g, '>')
			.replace(/&#40;/g, '(')
			.replace(/&#41;/g, ')')
			.replace(/&#39;/g, '\'')
			.replace(/&quot;/g, '"');
		return $sce.trustAsHtml(s);
    }
}]);
mainApp.controller('HomeController', function($scope, $http) {
	$http.get("home/articles").success(function(r){
		$scope.articles = r;
	});
});
mainApp.controller('ArticleController', function($scope, $http, $routeParams) {
	$http.get("article/"+$routeParams.id).success(function(r){
		$scope.article = r;
	});
});
mainApp.controller('AboutController', function($scope, $http) {
});
mainApp.controller('ContactController', function($scope, $http) {
});
