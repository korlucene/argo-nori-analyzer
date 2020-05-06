#### 2020.05.05 개선 내역 
```
1. 조사를 포함한 복합명사 분해 개선
  예) 국립과학원 : 국립(NNG),과(J),학원(NNG) -> 국립(NNG),과학(NNG),원(NNG)
     TestCase : org.apache.lucene.analysis.ko.TestMoprhDisplay.testDisplay()
     
2. 조사, 어미 Tag 추가
   원래 mecab-ko-dic 에서 조사와 어미의 태그는 여러 종류가 있으나,
   Nori에서는 조사(J), 어미(E)로만 태깅합니다.
   그런데 위 1.번 처럼 복합명사 개선을 처리하는데는,
   mecab-ko-dic이 원래 가지고 있던 모든 Tag 들이 필요하여 추가 하였습니다.
 
3. 사전 일부 개선
   mecab-ko-dic 을 일부 개선하여 압축하였습니다.
   
```