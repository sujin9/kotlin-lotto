# kotlin-lotto

# 기능 요구 사항
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.
- TDD로 구현한다.
- indent는 1까지만 허용한다.
- 함수(메서드)는 10라인까지 허용한다.
- Enum 클래스를 사용한다.
- 모든 원시 값과 문자열은 포장한다.
- 일급 컬렉션을 사용한다.

# 기능 목록
- [x] 구입 금액을 `입력`한다.
- [x] 금액을 통해 몇 장의 로또가 발급되는지 구한다.
- [ ] 구매한 로또 장 수를 `출력`한다.
- [x] 로또 발급: 범위 내의 중복되지 않은 랜덤 숫자 6개를 발생시킨다.
- [x] 구매한 장 수 만큼 로또를 발급한다.
- [ ] 발급한 로또를 `출력`한다.
- [ ] 당첨 번호를 `입력`한다.
- [ ] 보너스 볼을 `입력`한다.
- [x] 일치하는 번호 개수에 따라 Rank (enum)를 구한다.
- [x] 개수가 같을 때 보너스 넘버를 구분하여 Rank를 구한다.
- [x] 각 로또마다 위의 확인 과정을 거쳐 최종 당첨 통계를 구한다.
- [x] 최종 통계를 통해 당첨금을 계산한다.
- [x] 수익률을 계산한다.
- [ ] 당첨 통계를 `출력`한다.

## 예외 사항
- [x] 양의 정수가 아닌 값이 입력된 경우
- [x] (구입 금액) 로또 가격으로 나누어 떨어지지 않는 경우
- [x] (당첨 번호) 당첨 번호 개수가 모자르거나 초과되는 경우
- [x] (당첨 번호) 로또 번호 내에 중복된 번호가 있는 경우
- [x] (보너스 볼) 당첨 번호에 중복된 번호가 있는 경우
