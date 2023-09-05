import request from '@/utils/request'

// 查询需求管理列表
export function listRequirement(query) {
  return request({
    url: '/kyfz/requirement/list',
    method: 'get',
    params: query
  })
}

// 查询需求管理详细
export function getRequirement(requirementId) {
  return request({
    url: '/kyfz/requirement/' + requirementId,
    method: 'get'
  })
}

// 新增需求管理
export function addRequirement(data) {
  return request({
    url: '/kyfz/requirement',
    method: 'post',
    data: data
  })
}

// 修改需求管理
export function updateRequirement(data) {
  return request({
    url: '/kyfz/requirement',
    method: 'put',
    data: data
  })
}

// 删除需求管理
export function delRequirement(requirementId) {
  return request({
    url: '/kyfz/requirement/' + requirementId,
    method: 'delete'
  })
}

export function listenterprise(query) {
  return request({
    url: '/kyfz/requirement/listenterprise',
    method: 'get',
    params: query
  })
}

export function handleMatch(data) {
  return request({
    url: '/kyfz/requirement/handleMatch',
    method: 'post',
    params: data
  })
}

export function handleMatch_workstation(data) {
  return request({
    url: '/kyfz/requirement/handleMatch_workstation',
    method: 'post',
    params: data
  })
}

// 查询暂时的需求管理列表
export function listRequirement_staging(query) {
  return request({
    url: '/kyfz/requirement/list_staging',
    method: 'get',
    params: query
  })
}

// 新增暂时的需求管理
export function addRequirement_staging(data) {
  return request({
    url: '/kyfz/requirement/add_staging',
    method: 'post',
    data: data
  })
}

// 修改暂时的需求管理
export function updateRequirement_staging(data) {
  return request({
    url: '/kyfz/requirement/eidt_staging',
    method: 'put',
    data: data
  })
}

// 删除暂时的需求管理
export function delRequirement_staging(requirementId) {
  return request({
    url: '/kyfz/requirement/del_staging/' + requirementId,
    method: 'delete'
  })
}

// 查询暂时的需求管理详细
export function getRequirement_staging(requirementId) {
  return request({
    url: '/kyfz/requirement/get_staging/' + requirementId,
    method: 'get'
  })
}
