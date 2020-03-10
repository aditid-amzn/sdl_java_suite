"""
Enums transformation
"""

import logging
import textwrap
from collections import namedtuple, OrderedDict

from model.enum import Enum
from model.enum_element import EnumElement
from transformers.common_producer import InterfaceProducerCommon


class EnumsProducer(InterfaceProducerCommon):
    """
    Enums transformation
    """

    def __init__(self, paths):
        super(EnumsProducer, self).__init__(
            container_name='elements',
            enums_package=None,
            structs_package=None,
            package_name=paths.enums_package)
        self.logger = logging.getLogger('EnumsProducer')
        self._params = namedtuple('params', 'origin name internal description since value deprecated')

    @staticmethod
    def converted(name):
        if name[0].isdigit():
            name = '_' + name
        if '-' in name:
            name = name.replace('-', '_')
        return name

    def transform(self, item: Enum) -> dict:
        """
        Override
        :param item: particular element from initial Model
        :return: dictionary to be applied to jinja2 template
        """
        imports = set()
        params = OrderedDict()
        if any(map(lambda l: l.name != self.converted(l.name) or getattr(l, 'value', None) is not None,
                   getattr(item, self.container_name).values())):
            kind = 'custom'
            imports.add('java.util.EnumSet')
        else:
            kind = 'simple'
        return_type = 'String'

        for param in getattr(item, self.container_name).values():
            p = self.extract_param(param, kind)
            return_type = self.extract_type(param)
            params[p.name] = p

        render = OrderedDict()
        render['kind'] = kind
        render['return_type'] = return_type
        render['package_name'] = self.package_name
        render['class_name'] = item.name[:1].upper() + item.name[1:]
        render['params'] = params
        render['since'] = item.since
        render['deprecated'] = item.deprecated

        description = self.extract_description(item.description)
        if description:
            render['description'] = description
        if imports:
            render['imports'] = imports

        render['params'] = tuple(render['params'].values())
        if 'description' in render and isinstance(render['description'], str):
            render['description'] = textwrap.wrap(render['description'], 90)

        return render

    def extract_param(self, param: EnumElement, kind):
        d = {'origin': param.name}
        if kind == 'custom':
            d['name'] = self.converted(param.name)
            if getattr(param, 'value', None) is not None:
                d['internal'] = param.value
            else:
                d['internal'] = '"{}"'.format(param.name)
        elif kind == 'simple':
            d['name'] = self.converted(param.name)

        if getattr(param, 'since', None):
            d['since'] = param.since
        if getattr(param, 'deprecated', None):
            d['deprecated'] = param.deprecated
        if getattr(param, 'description', None):
            d['description'] = textwrap.wrap(self.extract_description(param.description), 90)
        Params = namedtuple('Params', sorted(d))
        return Params(**d)

    def extract_type(self, param: EnumElement) -> str:
        """
        Override
        Evaluate and extract type
        :param param: sub-element (EnumElement) of element from initial Model
        :return: string with sub-element type
        """
        if getattr(param, 'value') is not None:
            return 'int'
        else:
            return 'String'
