import requests
import json
import datetime

url = "https://graphql.anilist.co/?"

now = datetime.datetime.now()
pageNum = 1
season = "FALL"
seasonYear = now.year

payload="{\"query\":\"query ($season: MediaSeason, $seasonYear: Int, $format: MediaFormat, $pageNum: Int) {\\n    Page(page:$pageNum, perPage:80){\\n        pageInfo{\\n            total\\n            currentPage\\n            hasNextPage\\n        }\\n        media (season: $season, seasonYear: $seasonYear, format: $format type: ANIME) {    \\n            id    \\n            title {      \\n                romaji      \\n                english    \\n            }\\n            episodes\\n            nextAiringEpisode{\\n                airingAt\\n                episode\\n            }\\n        }\\n    }\\n}\",\"" \
        "variables\":{\"season\":\""+season+"\",\"seasonYear\":"+str(seasonYear)+",\"format\":\"TV\",\"pageNum\":"+str(pageNum)+"}}"
headers = {
  'Content-Type': 'application/json',
  'Cookie': 'laravel_session=ozMsPw2InRCIUvK1xqo1vlSbVjPayIFWSzAj6Y2I'
}

response = requests.request("POST", url, headers=headers, data=payload)
rez = json.loads(response.text)
print(rez)

with open("../../bin/post-result.json", "w") as fp:
    json.dump(rez, fp, indent=4, sort_keys=True)

