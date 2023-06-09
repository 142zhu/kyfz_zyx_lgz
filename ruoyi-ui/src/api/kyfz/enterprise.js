import request from '@/utils/request'

// 查询企业管理列表
export function listEnterprise(query) {
  return request({
    url: '/kyfz/enterprise/list',
    method: 'get',
    params: query
  })
}

// 查询企业管理详细
export function getEnterprise(enterpriseId) {
  return request({
    url: '/kyfz/enterprise/' + enterpriseId,
    method: 'get'
  })
}

// 新增企业管理
export function addEnterprise(data) {
  return request({
    url: '/kyfz/enterprise',
    method: 'post',
    data: data
  })
}

// 修改企业管理
export function updateEnterprise(data) {
  return request({
    url: '/kyfz/enterprise',
    method: 'put',
    data: data
  })
}

// 删除企业管理
export function delEnterprise(enterpriseId) {
  return request({
    url: '/kyfz/enterprise/' + enterpriseId,
    method: 'delete'
  })
}
