import request from '@/utils/request'

// 查询推送记录列表
export function listRecord(query) {
  return request({
    url: '/kyfz/record/list',
    method: 'get',
    params: query
  })
}

// 查询推送记录详细
export function getRecord(pushId) {
  return request({
    url: '/kyfz/record/' + pushId,
    method: 'get'
  })
}

// 新增推送记录
export function addRecord(data) {
  return request({
    url: '/kyfz/record',
    method: 'post',
    data: data
  })
}

// 修改推送记录
export function updateRecord(data) {
  return request({
    url: '/kyfz/record',
    method: 'put',
    data: data
  })
}

// 删除推送记录
export function delRecord(pushId) {
  return request({
    url: '/kyfz/record/' + pushId,
    method: 'delete'
  })
}
