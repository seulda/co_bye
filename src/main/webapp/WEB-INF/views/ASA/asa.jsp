<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
		
	<main role="main" class="main-content">
		<div class="container-fluid">
			<fmt:parseDate value="${td}" var="date" pattern="yyyyMMdd" />
			<div class="alert alert-primary" role="alert">&nbsp;<fmt:formatDate value="${date}" pattern="MM월 dd일"/> 기준&nbsp;&nbsp;|&nbsp;&nbsp;지역별 및 성별/연령별 확진자를 확인할 수 있습니다.</div>
			<div class="row justify-content-center">
				<div class="col-12">
					<div class="row">
						
						<!-- 지역별 확진자 그래프 -->
						<div class="col-md-12">
							<h4 style="text-align:center; margin:30px 0px;">지역별 확진자</h4>
							<div class="chart-box" style="padding:0px 20px;">
								<div id="columnChart1" style="margin: 20px auto; text-align: center;"></div>
							</div>
						</div>
						
						<!-- 지역별 확진자 표 start -->
						<div class="col-md-12">
							<table class="table table-borderless table-striped">
								<thead>
									<tr role="row">
										<th style="text-align:center;">지역</th>
										<th style="text-align:center;">기준 일시</th>
										<th style="text-align:center;">전체 확진자</th>
										<th style="text-align:center;">전일 대비</th>
										<th style="text-align:center;">사망자 수</th>
										<th style="text-align:center;">지역 발생</th>
										<th style="text-align:center;">해외 유입</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="alist" items="${area}">
										<tr>
											<th scope="col" style="text-align:center;">${alist.gubun}</th>
											<fmt:parseDate value="${alist.createDt}" var="dateTime" pattern="yyyy-MM-dd HH:mm:ss" />
											<td style="text-align:center;"><fmt:formatDate value="${dateTime}" pattern="MM월 dd일"/></td>
											<td style="text-align:center;"><fmt:formatNumber value="${alist.defCnt}" pattern="#,###,###" /> 명</td>  <!-- 전체 확진자 -->
											<td style="text-align:center;">+ <fmt:formatNumber value="${alist.incDec}" pattern="#,###,###" /></td> <!-- 전일 대비 -->
											<td style="text-align:center;"><fmt:formatNumber value="${alist.deathCnt}" pattern="#,###,###" /></td>	<!-- 사망자 수  -->
											<td style="text-align:center;"><fmt:formatNumber value="${alist.localOccCnt}" pattern="#,###,###" /></td><!-- 지역 발생  -->
											<td style="text-align:center;"><fmt:formatNumber value="${alist.overFlowCnt}" pattern="#,###,###" /></td>	<!-- 해외 유입 -->
											<input type="hidden" name="gubun" id="gubun" value="${alist.gubun}" />
											<input type="hidden" name="dateTime" id="dateTime" value="${dateTime}" />
											<input type="hidden" name="incDec" id="incDec" value="${alist.incDec}" />
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- 지역별 확진자 표 end -->
						
						<br><br><hr><br><br>
						
						<!-- gen/age section -->
						<div class="col-md-12">
							<c:set var="list" value="${genAge}"/>
							<c:choose>
								<c:when test="${list eq 'n'}">
									<div class="alert alert-danger" role="alert">
										<span class="fe fe-alert-circle fe-16 mr-2"></span>현재 공공데이터포털의 성별, 연령별 확진자 정보가 업데이트 되지 않아 정보 확인이 어려운 상태입니다.
									</div>
								</c:when>
								<c:when test="${list ne 'n'}">
									<!-- 연령별 확진자 그래프 start -->
									<div class="row my-4">
										<div class="col-md-12">
											<h4 style="text-align:center; margin:30px 0px;">연령별 확진자</h4>
											<div class="chart-box" style="padding:0px 20px;">
												<div id="columnChart" style="margin: 20px auto; text-align: center;"></div>
											</div>
										</div>
									</div>
									<!-- 연령별 확진자 그래프 end -->
									
									<!-- 성별/연령별 확진자 표 start -->
									<table class="table table-borderless table-striped">
										<thead>
											<tr role="row">
												<th style="text-align:center;">연령, 성별</th>
												<th style="text-align:center;">확진자</th>
												<th style="text-align:center;">확진률</th>
												<th style="text-align:center;">사망자</th>
												<th style="text-align:center;">사망률</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach var="blist" items="${genAge}">
											<tr>
												<th scope="col" style="text-align:center;">${blist.gubun}</th>
												<td style="text-align:center;"><fmt:formatNumber value="${blist.confCase}" pattern="#,###,###" /> 명</td>
												<td style="text-align:center;">${blist.confCaseRate}%</td>
												<td style="text-align:center;"><fmt:formatNumber value="${blist.death}" pattern="#,###,###" /> 명</td>
												<td style="text-align:center;">${blist.deathRate}%</td>
												<input type="hidden" name="confCase" id="confCase" value="${blist.confCase}" />
												<input type="hidden" name="death" id="death" value="${blist.death}" />
											</tr>
										</c:forEach>
										</tbody>
									</table>
									<!-- 성별/연령별 확진자 표 end -->
								</c:when>
							</c:choose>
						</div>
						<!-- gen/age section -->
					</div>
				</div>
			</div>
		</div>
	</main>
	
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/apexcharts.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/tinycolor-min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/config.js"></script>

<!-- 지역별 그래프 -->
<script>
	var areaData = [], D = [];
	<c:forEach var="area" items="${area}">
	    areaData.push('${area.gubun}');
	    D.push('${area.incDec}');
	</c:forEach>
	areaData.reverse();
	areaData.shift();
	D.reverse();
	D.shift();
	
	var columnChart, columnChartoptions = {
        series: [{
            name: "확진자",
            data: D,
        }],
        chart: {
            type: "bar",
            height: 350,
            stacked: !1,
            columnWidth: "95%",
            zoom: {
                enabled: !1
            },
            toolbar: {
                show: !1
            },
            background: "transparent"
        },
        dataLabels: { enabled: !1 },
        theme: { mode: colors.chartTheme },
        responsive: [{
            breakpoint: 480,
            options: {
                legend: {
                    position: "bottom",
                    offsetX: -10,
                    offsetY: 0
                }
            }
        }],
        plotOptions: {
            bar: {
                horizontal: !1,
                columnWidth: "12%",
                radius: 30,
                enableShades: !1,
                endingShape: "rounded",
                dataLabels: {
					position: 'top', // top, center, bottom
				},
            }
        },
        dataLabels: {
            enabled: true,
            offsetY: -30,
            style: {
              fontSize: '12px',
              colors: [colors.chartTheme]
            }
          },
        xaxis: {
            /* type: "datetime", */
            categories: areaData,
            labels: {
                show: !0,
                trim: !1,
                offsetX: 0,
                minHeight: void 0,
                maxHeight: 120,
                style: {
                    colors: colors.mutedColor,
                    cssClass: "text-muted",
                    fontFamily: base.defaultFontFamily
                }
            },
            axisBorder: { show: !1 }
        },
        yaxis: {
            labels: {
                show: !0,
                trim: !1,
                offsetX: -10,
                minHeight: void 0,
                maxHeight: 120,
                style: {
                    colors: colors.mutedColor,
                    cssClass: "text-muted",
                    fontFamily: base.defaultFontFamily
                }
            }
        },
        legend: {
            position: "top",
            fontFamily: base.defaultFontFamily,
            fontWeight: 400,
            labels: {
                colors: colors.mutedColor,
                useSeriesColors: !1
            },
            markers: {
                width: 10,
                height: 10,
                strokeWidth: 0,
                strokeColor: "#fff",
                fillColors: [extend.primaryColor, extend.primaryColorLighter],
                radius: 6,
                customHTML: void 0,
                onClick: void 0,
                offsetX: 0,
                offsetY: 0
            },
            itemMargin: {
                horizontal: 10,
                vertical: 0
            },
            onItemClick: { toggleDataSeries: !0 },
            onItemHover: { highlightDataSeries: !0 }
        },
        fill: {
            opacity: 1,
            colors: [base.primaryColor, extend.primaryColorLighter]
        },
        grid: {
            show: !0,
            borderColor: colors.borderColor,
            strokeDashArray: 0,
            position: "back",
            xaxis: {
                lines: { show: !1 }
            },
            yaxis: {
                lines: { show: !0 }
            },
            row: {
                colors: void 0,
                opacity: .5
            },
            column: {
                colors: void 0,
                opacity: .5
            },
            padding: {
                top: 0,
                right: 0,
                bottom: 0,
                left: 0
            }
        }
    },
    columnChartCtn = document.querySelector("#columnChart1");
	columnChartCtn && (columnChart = new ApexCharts(columnChartCtn, columnChartoptions)).render();
</script>

<!-- 연령별 그래프 -->
<script>
	var age = [], D = [];
	<c:forEach var="genAge" items="${genAge}">
	    age.push('${genAge.gubun}');
	    D.push('${genAge.confCase}');
	</c:forEach>
	D.length = 9;
	age.length = 9;
	
	var columnChart, columnChartoptions = {
        series: [{
            name: "확진자",
            data: D,
        }],
        chart: {
            type: "bar",
            height: 350,
            stacked: !1,
            columnWidth: "95%",
            zoom: {
                enabled: !1
            },
            toolbar: {
                show: !1
            },
            background: "transparent"
        },
        dataLabels: { enabled: !1 },
        theme: { mode: colors.chartTheme },
        responsive: [{
            breakpoint: 480,
            options: {
                legend: {
                    position: "bottom",
                    offsetX: -10,
                    offsetY: 0
                }
            }
        }],
        plotOptions: {
            bar: {
                horizontal: !1,
                columnWidth: "12%",
                radius: 30,
                enableShades: !1,
                endingShape: "rounded",
                dataLabels: {
					position: 'top', // top, center, bottom
				},
            }
        },
        dataLabels: {
            enabled: true,
            offsetY: -30,
            style: {
              fontSize: '12px',
              colors: [colors.chartTheme]
            }
          },
        xaxis: {
            /* type: "datetime", */
            categories: age,
            labels: {
                show: !0,
                trim: !1,
                offsetX: 0,
                minHeight: void 0,
                maxHeight: 120,
                style: {
                    colors: colors.mutedColor,
                    cssClass: "text-muted",
                    fontFamily: base.defaultFontFamily
                }
            },
            axisBorder: { show: !1 }
        },
        yaxis: {
            labels: {
                show: !0,
                trim: !1,
                offsetX: -10,
                minHeight: void 0,
                maxHeight: 120,
                style: {
                    colors: colors.mutedColor,
                    cssClass: "text-muted",
                    fontFamily: base.defaultFontFamily
                }
            }
        },
        legend: {
            position: "top",
            fontFamily: base.defaultFontFamily,
            fontWeight: 400,
            labels: {
                colors: colors.mutedColor,
                useSeriesColors: !1
            },
            markers: {
                width: 10,
                height: 10,
                strokeWidth: 0,
                strokeColor: "#fff",
                fillColors: [extend.primaryColor, extend.primaryColorLighter],
                radius: 6,
                customHTML: void 0,
                onClick: void 0,
                offsetX: 0,
                offsetY: 0
            },
            itemMargin: {
                horizontal: 10,
                vertical: 0
            },
            onItemClick: { toggleDataSeries: !0 },
            onItemHover: { highlightDataSeries: !0 }
        },
        fill: {
            opacity: 1,
            colors: [base.primaryColor, extend.primaryColorLighter]
        },
        grid: {
            show: !0,
            borderColor: colors.borderColor,
            strokeDashArray: 0,
            position: "back",
            xaxis: {
                lines: { show: !1 }
            },
            yaxis: {
                lines: { show: !0 }
            },
            row: {
                colors: void 0,
                opacity: .5
            },
            column: {
                colors: void 0,
                opacity: .5
            },
            padding: {
                top: 0,
                right: 0,
                bottom: 0,
                left: 0
            }
        }
    },
    columnChartCtn = document.querySelector("#columnChart");
	columnChartCtn && (columnChart = new ApexCharts(columnChartCtn, columnChartoptions)).render();
</script>


<%@ include file="../layout/footer.jsp"%>
