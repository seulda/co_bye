<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<!-- <meta name="title" content="COBYE_김다슬">
		<meta name="description" content="코로나19의 실시간 확진자 정보를 확인하세요.">
		<meta name="author" content="Daseul Kim"> -->
		<meta property="og:type" content="website">
		<meta property="og:title" content="COBYE_김다슬">
		<meta property="og:description" content="코로나19의 실시간 확진자 정보를 확인하세요.">
		<meta property="og:image" content="https://user-images.githubusercontent.com/57322002/130636145-acc1f0fc-9d48-407f-95e6-061f1df11bfe.jpg">
		<meta property="og:url" content="https://cobye.site">
		
		<link rel="icon" href="https://user-images.githubusercontent.com/57322002/130636298-0ed7099f-8267-4ca4-9dc8-b357fad113ee.png">
		<title>COBYE</title>
		<!-- Fonts CSS -->
		<link href="https://fonts.googleapis.com/css2?family=Overpass:ital,wght@0,100;0,200;0,300;0,400;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
		<!-- Icons CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/feather.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.steps.css">
		<!-- App CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app-light.css" id="lightTheme">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/app-dark.css" id="darkTheme" disabled>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/accordions.css">
		<!-- JQuery -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	</head>

	<body class="vertical light">
		<div class="wrapper">
			<nav class="topnav navbar navbar-light">
				<button type="button" class="navbar-toggler text-muted mt-2 p-0 mr-3 collapseSidebar">
					<i class="fe fe-menu navbar-toggler-icon"></i>
				</button>
				<ul class="nav">
					<li class="nav-item">
						<a class="nav-link text-muted my-2" href="#" id="modeSwitcher" data-mode="light">
							<i class="fe fe-sun fe-16"></i>
						</a>
					</li>
				</ul>
			</nav>
	
			<aside class="sidebar-left border-right bg-white shadow" id="leftSidebar" data-simplebar>
				<a href="#" class="btn collapseSidebar toggle-btn d-lg-none text-muted ml-2 mt-3" data-toggle="toggle">
					<i class="fe fe-x"><span class="sr-only"></span></i>
				</a>
				<nav class="vertnav navbar navbar-light">
					<!-- nav bar -->
					<div class="w-100 mb-4 d-flex">
						<a class="navbar-brand mx-auto mt-2 flex-fill text-center" href="/">&nbsp;&nbsp;
							<svg version="1.1" id="logo" class="navbar-brand-img brand-sm" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 120 120" xml:space="preserve">
				                <g>
				                  <polygon class="st0" points="78,105 15,105 24,87 87,87 	" />
				                  <polygon class="st0" points="96,69 33,69 42,51 105,51 	" />
				                  <polygon class="st0" points="78,33 15,33 24,15 87,15 	" />
				                </g>
				            </svg> 
	              			<span class="ml-2 item-text">&nbsp;&nbsp;COBYE</span>
						</a>
					</div>
	
					<ul class="navbar-nav flex-fill w-100 mb-2">
						<li class="nav-item w-100"><a class="nav-link" href="/">
							<i class="fe fe-home fe-16"></i><span class="ml-3 item-text">일별/누적 확진자</span>
						</a></li>
						<li class="nav-item w-100"><a class="nav-link" href="/asa">
							<i class="fe fe-layers fe-16"></i><span class="ml-3 item-text">지역/성별/연령별 확진자</span>
						</a></li>
						<li class="nav-item w-100"><a class="nav-link" href="/hospital">
							<i class="fe fe-flag fe-16"></i><span class="ml-3 item-text">선별진료소</span>
						</a></li>
						<li class="nav-item w-100"><a class="nav-link" href="/vaccine/vac">
							<i class="fe fe-map fe-16"></i><span class="ml-3 item-text">예방접종 의료기관</span>
						</a></li>
						<li class="nav-item w-100"><a class="nav-link" href="/beta">
								<i class="fe fe-activity fe-16"></i><span class="ml-3 item-text">(BETA) 실시간 확진자</span>
						</a></li>
						<li class="nav-item w-100"><a class="nav-link" href="/info">
								<i class="fe fe-alert-circle fe-16"></i><span class="ml-3 item-text">cobye</span>
						</a></li>
					</ul>
				</nav>
			</aside>