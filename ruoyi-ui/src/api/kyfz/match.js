import request from '@/utils/request'

// 查询匹配列表列表
export function listMatch(query) {
  return request({
    url: '/kyfz/match/list',
    method: 'get',
    params: query
  })
}

// 查询匹配列表详细
export function getMatch(matchId) {
  return request({
    url: '/kyfz/match/' + matchId,
    method: 'get'
  })
}

// 新增匹配列表
export function addMatch(data) {
  return request({
    url: '/kyfz/match',
    method: 'post',
    data: data
  })
}

// 修改匹配列表
export function updateMatch(data) {
  return request({
    url: '/kyfz/match',
    method: 'put',
    data: data
  })
}

// 删除匹配列表
export function delMatch(matchId) {
  return request({
    url: '/kyfz/match/' + matchId,
    method: 'delete'
  })
}
