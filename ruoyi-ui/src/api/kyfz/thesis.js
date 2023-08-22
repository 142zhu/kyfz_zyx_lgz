import request from '@/utils/request'

// 查询论文信息列表
export function listThesis(query) {
  return request({
    url: '/kyfz/thesis/list',
    method: 'get',
    params: query
  })
}

// 查询论文信息详细
export function getThesis(thesisId) {
  return request({
    url: '/kyfz/thesis/' + thesisId,
    method: 'get'
  })
}

// 新增论文信息
export function addThesis(data) {
  return request({
    url: '/kyfz/thesis',
    method: 'post',
    data: data
  })
}

// 修改论文信息
export function updateThesis(data) {
  return request({
    url: '/kyfz/thesis',
    method: 'put',
    data: data
  })
}

// 删除论文信息
export function delThesis(thesisId) {
  return request({
    url: '/kyfz/thesis/' + thesisId,
    method: 'delete'
  })
}
