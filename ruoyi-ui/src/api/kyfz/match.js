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
    url: '/kyfz/match/2/' + matchId,
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


//查询 匹配详细信息
//需要返回的信息包括需求、企业、推荐专家、专家研究方向、匹配分值、操作
//需求关键词requirement_keywords→根据匹配表的需求id获取，传输到前端之后需要对需求关键词进行处理（因为关键词是通过逗号隔开的）
//专家相关研究成果，通过匹配表中的专家账号（expertAccount）从专家表中获得，传输到前端之后也需要进行排序和二次处理
//专家团队，通过需求表中的专家账号获取专家表中所属的团队id，再根据团队id向团队表获取团队的成员信息（感觉团队这里可以加上团队成员的数据可视化）
export function getMatchDetails(matchIds) {
  return request({
    url: '/kyfz/match/' + matchIds,
    method: 'get'
  })
}

// 推送记录匹配列表
export function pushMatch(matchId) {
  return request({
    url: '/kyfz/match/' + matchId,
    method: 'put',
  })
}


// 修改匹配列表
export function updatePushRecord(data1) {
  return request({
    url: '/kyfz/match/3/',
    method: 'put',
    data: data1
  })
}