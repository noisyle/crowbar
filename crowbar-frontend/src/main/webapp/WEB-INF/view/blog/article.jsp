<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
    <header class="intro-header" style="background-image: url('${ctx}/static/site/img/blog/post-bg.jpg')">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="post-heading">
                        <h1 ng-bind-html="article.title"></h1>
                        <h2 class="subheading" ng-bind-html="article.subtitle"></h2>
                        <span class="meta">由 <a href="#">{{article.author.username}}</a> 发表于 {{article.publishtime | date : 'yyyy-MM-dd hh:mm:ss'}}</span>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Post Content -->
    <article>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1" ng-bind-html="article.content | unsafe">
                </div>
            </div>
        </div>
    </article>
