import request from '@/utils/request'

// 查询知识产权列表
export function listProperty(query) {
  return request({
    url: '/kyfz/property/list',
    method: 'get',
    params: query
  })
}

// 查询知识产权详细
export function getProperty(intellectualPropertyId) {
  return request({
    url: '/kyfz/property/' + intellectualPropertyId,
    method: 'get'
  })
}

// 新增知识产权
export function addProperty(data) {
  return request({
    url: '/kyfz/property',
    method: 'post',
    data: data
  })
}

// 修改知识产权
export function updateProperty(data) {
  return request({
    url: '/kyfz/property',
    method: 'put',
    data: data
  })
}

// 删除知识产权
export function delProperty(intellectualPropertyId) {
  return request({
    url: '/kyfz/property/' + intellectualPropertyId,
    method: 'delete'
  })
}
