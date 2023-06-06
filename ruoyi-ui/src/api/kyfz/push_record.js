import request from '@/utils/request'

// 查询推送记录列表
export function listPush_record(query) {
  return request({
    url: '/kyfz/push_record/list',
    method: 'get',
    params: query
  })
}

// 查询推送记录详细
export function getPush_record(enterpriseId) {
  return request({
    url: '/kyfz/push_record/' + enterpriseId,
    method: 'get'
  })
}

// 新增推送记录
export function addPush_record(data) {
  return request({
    url: '/kyfz/push_record',
    method: 'post',
    data: data
  })
}

// 修改推送记录
export function updatePush_record(data) {
  return request({
    url: '/kyfz/push_record',
    method: 'put',
    data: data
  })
}

// 删除推送记录
export function delPush_record(enterpriseId) {
  return request({
    url: '/kyfz/push_record/' + enterpriseId,
    method: 'delete'
  })
}
