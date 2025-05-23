# 구현 기능 목록

로또 게임은 아래와 같은 규칙으로 진행된다.

```
- 로또 번호의 숫자 범위는 1~45까지이다.
- 1개의 로또를 발행할 때 중복되지 않는 6개의 숫자를 뽑는다.
- 당첨 번호 추첨 시 중복되지 않는 숫자 6개와 보너스 번호 1개를 뽑는다.
- 당첨은 1등부터 5등까지 있다. 당첨 기준과 금액은 아래와 같다.
    - 1등: 6개 번호 일치 / 2,000,000,000원
    - 2등: 5개 번호 + 보너스 번호 일치 / 30,000,000원
    - 3등: 5개 번호 일치 / 1,500,000원
    - 4등: 4개 번호 일치 / 50,000원
    - 5등: 3개 번호 일치 / 5,000원
```



## 요구사항 살펴보기
- 참고해야할 주의 사항
  사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 종료한다.
```
[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.
```

- 로또 구입
1. 로또 1장 당 가격: 1,000 원 → 구입 금액에 해당하는 만큼 로또 발행
   `예외 처리`: 유저 구입 금액이 1,000원 단위로 나누어 떨어지지 않는 경우 예외 처리함
   (*IllegalArgumentException* 발생)

> 예외사항
> 1. 유저가 입력한 가격이 1,000원 단위가 아닌 경우
> 2. 숫자 외 값을 입력한 경우
> 3. 음수 값을 입력한 경우

- 로또 발행
1. 6개의 숫자 뽑기
2. 1~45 사이 숫자일 것
3. 중복되지 않는 숫자

- 당첨 번호 추첨
  당첨번호 6자리 입력 ( `,`로 구분 )
  → 공백 정도는 알아서 처리해서 받자
> 예외사항:
> 1. 숫자 외 값이 들어올 경우
> 2. 각각 숫자가 1-45 범위를 벗어나는 경우
> 3. 총 인자 개수가 6개가 아닌 경우
> 4. 중복된 숫자를 가지고 있을 경우

- 보너스 번호 1개 뽑기
> 예외사항:
> 1. 숫자 외 값이 들어올 경우
> 2. 숫자가 1-45 범위가 아닌 경우
> 3. 2개 이상 값이 들어오는 경우
> 4. 당첨 번호에 포함된 숫자를 뽑는 경우

- 당첨 판별
1. 로또 1장 당 번호 몇개 일치하는지 확인
2. 총 당첨 금액 계산 (enum 사용하기)

    - 1등: 6개 번호 일치 / 2,000,000,000원
    - 2등: 5개 번호 + 보너스 번호 일치 / 30,000,000원
    - 3등: 5개 번호 일치 / 1,500,000원
    - 4등: 4개 번호 일치 / 50,000원
    - 5등: 3개 번호 일치 / 5,000원

- 당첨 통계
1. 당첨 내역
2. 총 수익률 계산
   총 수익률 (%) = ( 구매한 총 금액 / 당첨 금액 ) * 100
    - 소숫점 2자리수에서 반올림

## 실행결과 예시
```
구입금액을 입력해 주세요.
8000

8개를 구매했습니다.
[8, 21, 23, 41, 42, 43] 
[3, 5, 11, 16, 32, 38] 
[7, 11, 16, 35, 36, 44] 
[1, 8, 11, 31, 41, 42] 
[13, 14, 16, 38, 42, 45] 
[7, 11, 30, 40, 42, 43] 
[2, 13, 22, 32, 38, 45] 
[1, 3, 5, 14, 22, 45]

당첨 번호를 입력해 주세요.
1,2,3,4,5,6

보너스 번호를 입력해 주세요.
7

당첨 통계
---
3개 일치 (5,000원) - 1개
4개 일치 (50,000원) - 0개
5개 일치 (1,500,000원) - 0개
5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
6개 일치 (2,000,000,000원) - 0개
총 수익률은 62.5%입니다.
```
