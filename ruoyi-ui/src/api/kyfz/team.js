import request from '@/utils/request'

// 查询专家团队列表
export function listTeam(query) {
  return request({
    url: '/kyfz/team/list',
    method: 'get',
    params: query
  })
}

// 查询专家团队详细
export function getTeam(teamId) {
  return request({
    url: '/kyfz/team/' + teamId,
    method: 'get'
  })
}

// 新增专家团队
export function addTeam(data) {
  return request({
    url: '/kyfz/team',
    method: 'post',
    data: data
  })
}

// 修改专家团队
export function updateTeam(data) {
  return request({
    url: '/kyfz/team',
    method: 'put',
    data: data
  })
}

// 删除专家团队
export function delTeam(teamId) {
  return request({
    url: '/kyfz/team/' + teamId,
    method: 'delete'
  })
}
