# kakaopaySecurities
2021.07 카카오페이증권 과제

## Spec
 
 Framework : springframework.boot version 2.5.2 (Gradle Project)
 
 Language : Java version 11
 
 DB : H2 InMemory

## 빌드 및 실행 방법

1. 인메모리 설정

		url: jdbc:h2:tcp://localhost/~/kakaosecurities

		username: sa


2. 문제 1~4 : Get Request ( localhost:8080/problem1 ~ localhost:8080/problem4 )

	문제 4번은 Body Mapping 필요 	
	
		{
		 "brName" : "잠실점"
		}


## 문제해결 방법

### 요구사항에 부합하는 SQL 작성 (Repository) 및 서비스, 컨트롤러 개발

	1. Domain 설계
	2. SQL 작성
	3. 문제별 Service 개발 ( 결과를 Interface 에 매핑 )
	4. Controller Call
	5. 테스트 코드 작성 ( SQL 결과값과 Fetch 의 결과값이 동일한지 , 해당 요구사항에 부합한지 )

### 테스트 코드

#### testInsertNumber : CSV File 정상 업로드 확인
	kakapaySecurities.testprogram.service.insertData.InsertAllDataServiceTest
	
#### Task1 ~ 4 테스트 코드	
	kakapaySecurities.testprogram.controller.SolveControllerTest
	kakapaySecurities.testprogram.repository.BranchRepositoryTest
	kakapaySecurities.testprogram.repository.DealHistoryRepositoryTest
	kakapaySecurities.testprogram.service.task.TaskService4Test

##### Task1 2018년, 2019년 각 연도별 합계 금액이 가장 많은 고객 추출

	1. task1TestByStaticValue : 쿼리 결과값에 대한 value 검증
	2. task1TestByCompareAllData : ( 전체 거래건수 Fetch 및 HashMap 에 적재한 계좌별 금액 )과 쿼리 결과값 검증

##### Task2 2018년 또는 2019년에 거래가 없는 고객 추출

	1. task2TestByExistsData : 쿼리 결과값의 계좌에 해당년도 거래가 존재하는지 검증

##### Task3 연도별 관리점별 거래금액 합계를 구하고 합계금액이 큰 순서로 출력

	1. task3TestByResultSize : 쿼리의 결과값이 전지점에 대해서 이뤄지는지 검증
	2. task3TestByResultOrder : 쿼리의 결과값이 합계금액이 큰 순서로 출력되는지 검증
	3. task3TestByAllData : ( 전체 거래건수 Fetch 및 HashMap 에 적재한 지점별 금액 )과 쿼리 결과값 검증

##### Task4 지점명을 입력하면 해당지점의 거래금액 합계를 출력 ( 분당점과 판교점을 통폐합하여 판교점으로 관리점 이관을 하였습니다. )

	1. task4TestBySumAllYear : 전체 거래에 대해 금액을 전부 합산하고 있는지 확인
	2. task4TestForBranchIntegration : 판교점을 검색했을 때, 분당점의 금액까지 합산하여 나오는지 확인
	3. task4TestForResponse : 분당점 및 존재하지 않는 지점 검색시 404 에러 확인

