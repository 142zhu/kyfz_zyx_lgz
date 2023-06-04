import request from '@/utils/request'

// 查询专家信息列表
export function listZjxx(query) {
  return request({
    url: '/kyfz/zjxx/list',
    method: 'get',
    params: query
  })
}

// 查询专家信息详细
export function getZjxx(id) {
  return request({
    url: '/kyfz/zjxx/' + id,
    method: 'get'
  })
}

// 新增专家信息
export function addZjxx(data) {
  return request({
    url: '/kyfz/zjxx',
    method: 'post',
    data: data
  })
}

// 修改专家信息
export function updateZjxx(data) {
  return request({
    url: '/kyfz/zjxx',
    method: 'put',
    data: data
  })
}

// 删除专家信息
export function delZjxx(id) {
  return request({
    url: '/kyfz/zjxx/' + id,
    method: 'delete'
  })
}
