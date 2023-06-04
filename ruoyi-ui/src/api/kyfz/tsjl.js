import request from '@/utils/request'

// 查询推送记录列表
export function listTsjl(query) {
  return request({
    url: '/kyfz/tsjl/list',
    method: 'get',
    params: query
  })
}

// 查询推送记录详细
export function getTsjl(id) {
  return request({
    url: '/kyfz/tsjl/' + id,
    method: 'get'
  })
}

// 新增推送记录
export function addTsjl(data) {
  return request({
    url: '/kyfz/tsjl',
    method: 'post',
    data: data
  })
}

// 修改推送记录
export function updateTsjl(data) {
  return request({
    url: '/kyfz/tsjl',
    method: 'put',
    data: data
  })
}

// 删除推送记录
export function delTsjl(id) {
  return request({
    url: '/kyfz/tsjl/' + id,
    method: 'delete'
  })
}
