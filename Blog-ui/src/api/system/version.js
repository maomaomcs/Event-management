import request from '@/utils/request'

// 查询可视化界面列表
export function listVersion(query) {
  return request({
    url: '/system/version/list',
    method: 'get',
    params: query
  })
}

// 查询可视化界面详细
export function getVersion(id) {
  return request({
    url: '/system/version/' + id,
    method: 'get'
  })
}

// 新增可视化界面
export function addVersion(data) {
  return request({
    url: '/system/version',
    method: 'post',
    data: data
  })
}

// 修改可视化界面
export function updateVersion(data) {
  return request({
    url: '/system/version',
    method: 'put',
    data: data
  })
}

// 删除可视化界面
export function delVersion(id) {
  return request({
    url: '/system/version/' + id,
    method: 'delete'
  })
}
