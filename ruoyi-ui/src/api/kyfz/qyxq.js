import request from '@/utils/request'

// 查询企业需求信息列表
export function listQyxq(query) {
  return request({
    url: '/kyfz/qyxq/list',
    method: 'get',
    params: query
  })
}

// 查询企业需求信息详细
export function getQyxq(id) {
  return request({
    url: '/kyfz/qyxq/' + id,
    method: 'get'
  })
}

// 新增企业需求信息
export function addQyxq(data) {
  return request({
    url: '/kyfz/qyxq',
    method: 'post',
    data: data
  })
}

// 修改企业需求信息
export function updateQyxq(data) {
  return request({
    url: '/kyfz/qyxq',
    method: 'put',
    data: data
  })
}

// 删除企业需求信息
export function delQyxq(id) {
  return request({
    url: '/kyfz/qyxq/' + id,
    method: 'delete'
  })
}
