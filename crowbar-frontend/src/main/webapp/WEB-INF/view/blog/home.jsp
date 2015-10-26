<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
    <header class="intro-header" style="background-image: url('${ctx}/static/site/img/blog/home-bg.jpg')">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="site-heading">
                        <h1>L&F STUDIO</h1>
                        <hr class="small">
                        <span class="subheading">Lee & Friends Studio</span>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Main Content -->
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <div class="post-preview" ng-repeat="article in articles">
                    <a href="#/article/{{article.id}}">
                        <h2 class="post-title" ng-bind-html="article.title"></h2>
                        <h3 class="post-subtitle" ng-bind-html="article.subtitle"></h3>
                    </a>
                    <p class="post-meta">由 <a href="#">{{article.author.username}}</a> 发表于 {{article.publishtime | date : 'yyyy-MM-dd hh:mm:ss'}}</p>
	                <hr>
                </div>
                <!-- Pager -->
                <ul class="pager">
                    <li class="next">
                        <a href="#">Older Posts &rarr;</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
