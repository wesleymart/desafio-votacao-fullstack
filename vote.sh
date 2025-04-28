#!/bin/bash
if [ -z "$BASH_VERSION" ]; then
  echo "Este script deve ser executado com bash, e não com sh!"
  exit 1
fi


send_vote() {
  INDEX=$1
  # Gera um CPF aleatório (11 dígitos)
  CPF=$(shuf -i 10000000000-99999999999 -n 1)
  DISCUSS_ID=4  # fixo
  VOTE_OPTION=$((RANDOM % 2)) # 0 ou 1

  if [ "$VOTE_OPTION" -eq 0 ]; then
    VOTE="sim"
  else
    VOTE="não"
  fi

  RESPONSE=$(curl -s -w "HTTPSTATUS:%{http_code}" -X POST "http://localhost:8080/api/vote" \
    -H "accept: */*" \
    -H "Content-Type: application/json" \
    -d "{
      \"associatedCpf\": \"${CPF}\",
      \"discussId\": ${DISCUSS_ID},
      \"vote\": \"${VOTE}\"
    }")

  BODY=$(echo "$RESPONSE" | sed -e 's/HTTPSTATUS\:.*//g')
  STATUS=$(echo "$RESPONSE" | tr -d '\n' | sed -e 's/.*HTTPSTATUS://')

  echo -e "\n[$INDEX] Status: $STATUS"
  echo "[$INDEX] Body: $BODY"
}

export -f send_vote

TOTAL_REQUESTS=20000

seq 1 $TOTAL_REQUESTS | xargs -n1 -P$TOTAL_REQUESTS -I{} bash -c "send_vote {}"

