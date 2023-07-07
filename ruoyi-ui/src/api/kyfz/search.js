import request from '@/utils/request'

// 查询检索列表
export function listSearch(query) {
  return request({
    url: '/kyfz/search/list',
    method: 'get',
    params: query
  })
}

// 查询检索详细
export function getSearch(searchId) {
  return request({
    url: '/kyfz/search/' + searchId,
    method: 'get'
  })
}

// 新增检索
export function addSearch(data) {
  return request({
    url: '/kyfz/search',
    method: 'post',
    data: data
  })
}

// 修改检索
export function updateSearch(data) {
  return request({
    url: '/kyfz/search',
    method: 'put',
    data: data
  })
}

// 删除检索
export function delSearch(searchId) {
  return request({
    url: '/kyfz/search/' + searchId,
    method: 'delete'
  })
}
