import request from '@/utils/request'

// 查询专家关系列表
export function listRelationship(query) {
  return request({
    url: '/kyfz/relationship/list',
    method: 'get',
    params: query
  })
}

// 查询专家关系详细
export function getRelationship(relationshipId) {
  return request({
    url: '/kyfz/relationship/' + relationshipId,
    method: 'get'
  })
}

// 新增专家关系
export function addRelationship(data) {
  return request({
    url: '/kyfz/relationship',
    method: 'post',
    data: data
  })
}

// 修改专家关系
export function updateRelationship(data) {
  return request({
    url: '/kyfz/relationship',
    method: 'put',
    data: data
  })
}

// 删除专家关系
export function delRelationship(relationshipId) {
  return request({
    url: '/kyfz/relationship/' + relationshipId,
    method: 'delete'
  })
}
